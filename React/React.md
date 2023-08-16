## React

### What is React?
* 리액트는 단일 페이지 애플리케이션(SPA) 구추겡 가장 인기있는 `JavaScript` 라이브러리 중 하나이다. 
* 리액트는 컴포넌트의 조합으로 애플리케이션을 만든다. 즉, 리액트는 컴포넌트 기반이다. 
* 컴포넌트라는 서로 다른 모듈을 여러개 만들 수 있고 이 컴포넌트를 활용해 전체 애플리케이션을 만든다. 
* 리액트는 주로 프론트엔드 웹 SPA 애플리케이션을 만드는데 사용되지만, `Android`나 `IOS native application`을 만드는데도 사용할 수 있다. 

### How to create a project
* 해당 폴더로 이동
* `$ npm create-react-app {project name}` 으로 react project 생성
* `$ npm start`로 실행

### React Initialization
* public /index.html : Contains root div
* src/index.js : Initializes React App. Loads App component
  * src/index.css : styling for entire application
* src/App.js : Code for app component
  * src/App.css : styling for App component
  * src/app.test.js : Unit tests for App component
  
### Component
* `index.js`에 가보면 app component가 여기에서 로드되는 것을 볼 수 있다. 
* 이는 첫 번째로 로드되는 컴포넌트이고 보통은 다른 컴포넌트를 만들 때 커모넌트의 자식으로 만든다. 
* HTML과 비슷한 view가 있는데, 리액트는 JSX를 사용하며 js를 활용해 로직을 정의할 수 있다. 
* react component에는 view, logic, style정보 외에 State, Props라는 것이 있다. 
  * State는 컴포넌트의 내부 데이터 저장소와 같다.
  * Props는 사용하면 컴포넌트 사이 데이터를 전달할 수 있다. 

### Getting Started with JSX - Views with React
* 대부분이 리액트 프로젝트는 프레젠테이션을 JSX로 한다. 
* JSX는 JavaScript XML의 약자이다. 
* 이는 HTML 보다 엄격한데, 닫는 태그가 필수이며 최상위 태그는 하나만 가능하다.
* 이는 여러 개의 최상의 JSX 태그를 반환할 수 없으며 공유 부모로 묶어주어야 한다.
* `<div>`로 묶거나 빈 태그(`< >`)를 상단에 만들 수 있다. 

### Background
* 우리가 상태를 업데이트하는 동안 리액트는 백그라운드에서 아주 많은 작업을 수행한다.
* 컴포넌트의 상태가 조금이라도 변경되면 리액트는 가상 DOM 부터 업데이트 한다. 
* 그런 뒤, 업데이트한 가상 DOM을 이전 버전의 가상 DOM과 비교하여 차이점을 파악하고 달라진 부분을 HTML 페이지에 반영해 업데이트한다.
* 이는 동기화 작업을 수행한다고 이해하면 쉽다.

