//즉시실행함수로 만듦  (function(){ return {}; })()
//키와 메서드를 가진 JavaScript Object를 return함 
//Restfull방식으로 요청하기
 
 //토근을 header에 저장해서 들고 다니기
 //ajax할 때도 필요하므로 beforeSend 포함해서 전송하기
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
 
 
var externalFunc = (() => {
	return {myname: "tm",
			mycompanay: "신한",
			work: () => {
				alert("외부함수")
			}
	}
})();

var replyManager = (function() {
    ///특정board의 댓글가져오기 ==> replies/100
	var getAll2 = function(obj, callback) {
		console.log("get All.....");
		//getJON을 실행하면 callback을 실행해라
		$.getJSON("/app/replies/" + obj, callback);
	};
	
	//선언적 함수
	function beforeSend(xhr){
		xhr.setRequestHeader(header, token);
	}
	
	//익명함수를 만들어서 변수 할당
	/*var beforeSend2 = function (xhr){
		xhr.setRequestHeader(header, token);
	}*/
	
    //board의 댓글추가 {"bno":11, title:"aa", writer:"bb"}
	var add2 = function(obj, callback){
		console.log("add.....");
	    $.ajax({
		    beforeSend : beforeSend,
			type:"post",
			url: "/app/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
	   });
	};
	
	//댓글수정
    var update2 = function(obj, callback) {
		$.ajax({
			beforeSend : beforeSend,
			type: "put",
			url:"/app/replies/" +  obj.bno,
			data:JSON.stringify(obj),
			dataType:"json",
			contentType:"application/json",
			success:callback
		});
    };
    
    var remove2 = function(obj, callback)  {
	$.ajax({
		beforeSend : beforeSend,
		type:  "delete",
		url:  "/app/replies/"+obj.bno + "/" + obj.rno,
		dataType: "json",
		contentType: "application/json",
		success: callback
	});
  };
   return { getAll: getAll2, 
   			add: add2, 
   			update: update2, 
   			remove: remove2 };
}) ();