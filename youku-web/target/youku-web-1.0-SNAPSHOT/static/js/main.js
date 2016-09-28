$(function(){
	$("#login-btn").click(function(){
	  $.get("/api?id=4",function(data,status){
	    alert(data.id);
	  });
	});
})