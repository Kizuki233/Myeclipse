$(function(){
	console.log($(".submitAdd"));
	$(".submitAdd").click(function(){
		console.log("in submitAdd...");
		console.log($(".userName").val()
				+" "+$(".userPassword").val()
				+" "+$(".userTelephone").val()
				+" "+$(".userGender").val()
				+" "+$(".userAge").val());
		var obj={};
		obj['user.name']=$(".userName").val();
		obj['user.password']=$(".userPassword").val();
		obj['user.telephone']=$(".userTelephone").val();
		obj['user.gender']=$(".userGender").val();
		obj['user.age']=$(".userAge").val();
		//console.log(obj);
		$.post("saveUser.action", obj, function(){
			console.log("发送成功");
		})
	});
});