<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/ex.js"></script>

<style type="text/css">
body {
   font-size: 18px;
}

.grid {
   padding: 10px 50px 30px;
   margin: 50px auto;
   width: 600px;
   background: #fff;
   border: 2px solid #eed0dc;
   border-radius: 20px;
   box-shadow: 5px 5px 5px #cbcbcb;
}

.grid h1 {
   color: #333;
   font-size: 2.4em;
   text-align: center;
   ;
}

#question {
   padding: 10px 2em;
   background: salmon;
   border-radius: 15px;
   font-size: 24px;
   color: #fff;
}

#quiz {
   text-align: center;
}

.buttons {
   padding: 30px 20px;
   border: 2px solid #eed0dc;
   border-radius: 20px;
}

.btn, .next {
   margin: 10px 40px 20px 0;
   padding: 10px;
   width: 250px;
   font-size: 16px;
   color: #333;
   background: #eed0dc;
   border: 1px solid #ffe3ed;
   border-radius: 15px;
   cursor: pointer;
   transition: all .2s;
}

/* .btn:nth-child(2n) { */
/*    margin-right: 0; */
/* } */

.btn:hover, .next:hover {
   background: #c34c74;
   color: #fff;
}

</style>



</head>
<body>
<div class="grid">
   <h1>퀴즈</h1>
   <div id="quiz">
      <p id="question">
      	${vo.no }.${vo.quiz }
      </p>

	<div class="buttons">
		<button class="btn">1.</button>
		<button class="btn">2.</button>
		<button class="btn">3.</button>
		<button class="btn">4.</button>
	</div>

      <footer>
         <button class="next">다음 문제로</button>
      </footer>
   </div>
</div>

</body>
</html>