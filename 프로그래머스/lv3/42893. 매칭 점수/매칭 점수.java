import java.util.*;
import java.util.regex.*;

class Solution {
     Map<String, Page> linkMap = new HashMap();

    //         destination sources
     // Map<String, List<String>> linkGraph = new HashMap();
    
    public int solution(String word, String[] pages) {
        List<Page> pageList = new ArrayList();

       for(String page:pages){
           pageList.add(parsePage(page, word));
       }    

        for(Page page:pageList){
            page.updateLinkPoints();
        }

        double max = 0;
        int res = 0;
        for(int i=0;i<pageList.size();i++){
            Page p = pageList.get(i);
            // System.out.println(p.toString());
            double matchPoints = p.getMatchPoints();
            if(max < matchPoints){
                res = i;
                max = matchPoints;
            }
        }

        return res;
    }

    Page parsePage(String p, String word){
        Page page = new Page();

        Matcher m = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")").matcher(p);
        while(m.find()){
            page.url = m.group(2).trim();
        }      
        
        // 9번 11번 틀릴 때 regex  = [^a-zA-Z]+(%s)[^a-zA-Z]
        m = Pattern.compile(String.format("(?<=[^a-zA-Z])(%s)[^a-zA-Z]", word.toLowerCase())).matcher(p.toLowerCase());
        while(m.find()){
            page.basePoints++;
        }
        
        m = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"").matcher(p);
        while(m.find()){
            String href = m.group(2).trim();
            page.hyperLinks.add(href);
        }
        
        linkMap.put(page.url, page);
        return page;
    }

     class Page{
        String url;
        double basePoints = 0;
        double linkPoints = 0;
        List<String> hyperLinks = new ArrayList();
         
        void updateLinkPoints(){
            for(String link : hyperLinks){
                if(linkMap.containsKey(link)){
                    linkMap.get(link).linkPoints += basePoints/hyperLinks.size();
                }
            }
        }

        double getMatchPoints(){
            return basePoints + linkPoints;
        }

        @Override
        public String toString(){

            return url + "\n"
                + "BasePoints : " + basePoints + "\n"
                + "LinkPoints : "  + linkPoints + "\n";
        }
    }
}