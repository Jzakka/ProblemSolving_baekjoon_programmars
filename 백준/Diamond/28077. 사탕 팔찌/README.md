# [Diamond V] 사탕 팔찌 - 28077 

[문제 링크](https://www.acmicpc.net/problem/28077) 

### 성능 요약

메모리: 365968 KB, 시간: 2704 ms

### 분류

오일러 경로, 그래프 이론, 해싱, 문자열

### 문제 설명

<p>안즈는 사탕을 정말 좋아한다.</p>

<p>안즈는 매일 사탕을 먹다 보니 이제는 질리기 시작했고, 사탕을 먹는 새로운 방법을 찾아보고자 한다. 그 방법은 여러 종류의 사탕을 일렬로 늘어놓은 다음, 한 번에 먹는 것이다! 이런 식으로 <strong>사탕 묶음</strong>을 만들어 먹으면 적은 종류의 사탕으로도 다양한 맛을 느낄 수 있게 된다.</p>

<p>안즈는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(N\)</span></mjx-container>종류의 사탕중에 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43E TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>K</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(K\)</span></mjx-container>개의 서로 다른 사탕을 골라 사탕 묶음을 만들어 먹기로 했다. 예를 들어, 딸기, 오렌지, 포도 3종류의 사탕 중에 2개의 서로 다른 사탕을 골라 사탕 묶음을 만들기로 했다면, 안즈는 (딸기, 오렌지), (딸기, 포도), (오렌지, 딸기), (오렌지, 포도), (포도, 딸기), (포도, 오렌지)의 6가지 새로운 맛을 경험할 수 있다.</p>

<p>안즈는 이러한 방식으로 만들어진 사탕 묶음들을 살펴보며 만족스러워했다. 그러던 중, 모든 사탕 묶음을 한 번씩 사용하여 원형의 사탕 팔찌를 만들면 더 귀엽겠다는 생각이 들었다.</p>

<p>안즈는 사탕 팔찌의 이웃하는 두 사탕 묶음에 대해, 이전 사탕 묶음의 뒤쪽 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43E TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="3"><mjx-c class="mjx-c2212"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="3"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>K</mi><mo>−</mo><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(K-1\)</span></mjx-container>개의 사탕 배열과 다음 사탕 묶음의 앞쪽 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43E TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="3"><mjx-c class="mjx-c2212"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="3"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>K</mi><mo>−</mo><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(K-1\)</span></mjx-container>개의 사탕 배열이 같도록 사탕을 꿰기로 했다.</p>

<p>예를 들어, (딸기, 오렌지) 뒤에는 (오렌지, 딸기) 또는 (오렌지, 포도)가 올 수 있지만, (포도, 딸기)는 올 수 없다.</p>

<p>또, (딸기, 오렌지, 포도) 뒤에는 (오렌지, 포도, 딸기)가 올 수 있지만, (포도, 딸기, 오렌지) 와 (오렌지, 딸기, 포도)는 올 수 없다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/39918316-c2d1-4605-83fe-6e5bfa1bb42e/-/preview/" style="height: 449px; width: 500px;"></p>

<p>그러나 안즈는 이러한 조건을 만족하는 사탕 팔찌를 만들 수 있는지 모른다. 안즈는 이 문제를 풀기에는 너무 귀찮았기 때문에, 여러분에게 해결을 부탁했다.</p>

### 입력 

 <p>첫째 줄에 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(N\)</span></mjx-container>과 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43E TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>K</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(K\)</span></mjx-container>가 공백으로 구분되어 주어진다.</p>

### 출력 

 <p>사탕 팔찌를 완성할 수 있다면, 첫째 줄에 "<code>YES</code>"를 출력하고, 둘째 줄에 사탕 팔찌를 구성하는 사탕 묶음을 차례대로 공백으로 구분하여 출력한다. 가능한 답이 여러 개라면, 그 중 아무거나 하나를 출력한다.</p>

<p>사탕 팔찌는 만들 수 있는 모든 사탕 묶음을 한 번씩 포함해야 하며, 지문에서 설명한 내용을 만족해야 한다. 사탕 팔찌는 원형이므로, 마지막으로 출력한 사탕 묶음의 다음 사탕 묶음은 맨 처음에 출력한 사탕 묶음이다.</p>

<p>각 사탕은 알파벳 대문자의 첫 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(N\)</span></mjx-container>개를 사용하여 나타내며, 하나의 사탕 묶음은 그 사탕 묶음을 이루는 사탕들을 차례대로 늘어놓은 하나의 문자열로 나타낸다.</p>

<p>사탕 팔찌를 완성할 수 없다면, 첫째 줄에 "<code>NO</code>"를 출력한다.</p>

