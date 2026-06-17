<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
const commons=(page)=>{
	let types=[]
	$('input[name=type]:checked').each(function(){
		types.push($(this).val())
	})
/* 	console.log(types) */
	let ss=$('#ss').val()
	let column=$('#column').val()
/* 	console.log("ss:"+ss)
	console.log("column:"+column) */
	$.ajax({
		type:'post',
		url:'../food/find_ajax.do',
		data:{"ss":ss,"column":column,"type":types,"page":page},
		traditional:true, // 배열 쓸때 안쓰면 null값 됨
		success:(result)=>{
			let json=JSON.parse(result)
			console.log(json)
			$('#ss').val(json[0].ss)
			jsonView(json)
		}
	})
}
const jsonView=(json)=>{
	let html=''
	json.forEach((food)=>{
		html+='<div class="col-sm-3">'
			+'<a href="#">'
			+'<div class="thumbnail">'
			+'<img src="'+food.poster+'" style="width:250px;height:130px">'
			+'</div>'
			+'<p>'+food.name+'</p>'
			+'</a>'
			+'</div>'
	})
	$('#print').html(html)
}
$((e)=>{
	commons(1)
	$('.btns').on('click',()=>{
		let types=[]
		let count=$("input[name=type]:checked").length
		if(count===0){
			alert("체크박스에 체크를 하세요")
			return
		}
		$("input[name=type]:checked").each(function(){
			types.push($(this).val())
		})
		let ss=$('#ss').val()
		if(ss.trim()===""){
			$('#ss').focus()
			return
		}
		let column=$('#column').val()
		$.ajax({
			type:'post',
			url:'../food/find_ajax.do',
			data:{"ss":ss,"column":column,"type":types,"page":1},
			traditional:true, // 배열 쓸때 안쓰면 null값 됨
			success:(result)=>{
				let json=JSON.parse(result)
				console.log(json)
				$('#ss').val(json[0].ss)
				jsonView(json)
			}
		})
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- <form method="post" action="find.do"> -->
				<select id="column" class="input-sm">
					<option value="address">주소</option>
					<option value="name">업체명</option>
				</select>
				<input type="checkbox" name="type" value="A">한식
				<input type="checkbox" name="type" value="B">양식
				<input type="checkbox" name="type" value="C">일식
				<input type="checkbox" name="type" value="D">중식
				<input type="checkbox" name="type" value="E">분식
				<input type="text" id="ss" class="input-sm" size="15" placeholder="검색어를 입력해주세요" value="마포">
				<button class="btn btn-sm btn-primary btns">검색</button>
			<!-- </form> -->
		</div>
		<div class="row" style="margin-top: 20px" id="print">
			<%-- <c:forEach var="vo" items="${list }">
				<div class="col-sm-3">
					<a href="#">
						<div class="thumbnail">
							<img src="${vo.poster }" style="width: 250px;height: 130px">
						</div>
						<p>${vo.name }</p>
					</a>
				</div>
			</c:forEach> --%>
			
		</div>
		<div class="row text-center" style="margin-top: 20px" id="pagePrint">
			
		</div>
	</div>
</body>
</html>