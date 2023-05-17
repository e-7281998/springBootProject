//즉시실행함수로 만듦  (function(){ return {}; })()
//키와 메서드를 가진 JavaScript Object를 return함  
​
var replyManager = (function() {
    ///특정board의 댓글가져오기 ==> replies/100
	var getAll2 = function(obj, callback) {
		console.log("get All.....");
		$.getJSON("/app/replies/" + obj, callback);
	};
​
    //board의 댓글추가 {"bno":11, title:"aa", writer:"bb"}
	var add2 = function(obj, callback){
		console.log("add.....");
	    $.ajax({
			type:"post",
			url: "/myapp/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
	   });
	};
	//댓글수정
    var update2 = function(obj, callback) {
		$.ajax({
			type: "put",
			url:"/myapp/replies/" +  obj.bno,
			data:JSON.stringify(obj),
			dataType:"json",
			contentType:"application/json",
			success:callback
		});
    };
​
    var remove2 = function(obj, callback)  {
	$.ajax({
		type:  "delete",
		url:  "/app/replies/"+obj.bno + "/" + obj.rno,
		dataType: "json",
		contentType: "application/json",
		success: callback
	});
​
  };
   return { getAll: getAll2, 
   			add: add2, 
   			update: update2, 
   			remove: remove2 };
}) ();