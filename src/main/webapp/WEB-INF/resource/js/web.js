 $(function(){
	 $("#btn").on("click",function(){
		 var data={"name":$("#hide_ele").val(),"pwd":"123456"};
		 console.log(data);
		 $.ajax({
			 type:"POST",
			 url:"/user/login.html",
			  contentType : 'application/json',
			 data:JSON.stringify(data),
			 success:function(result){
				 console.log(result);
			 }
		 });
	 });

	 $("#btn2").on("click",function(){
	 	$.ajax({
			 type:"GET",
			 dataType:"json",
			 url:"/user/json_view.html",
			 success:function(result){
				 console.log(result);
				 alert(result[0].userName);
			 }
		 });
	 });
 });
