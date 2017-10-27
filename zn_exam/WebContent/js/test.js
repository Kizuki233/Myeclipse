$(function(){

	$(".sb").click(function(){
		console.log("in sb...");
		console.log($(".na").val());
		var obj={};
		obj['department.name']=$(".na").val();
		console.log(obj);
		$.post("saveDepartment.action",obj,function(){
			console.log("发送成功");
		});
		
	});
	
});
