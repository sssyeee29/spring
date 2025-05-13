console.log("Reply Module.......");

//즉시 실행함수 
let replyService = (function(){

    //db에 값이 잘 들어가는지 확인함
    function add(reply, callback, error){
        console.log("add reply................");
        
        $.ajax({ //비동기통신  //ajax을 쓰면 비동기통신
            type : 'post',
            url : '/replies/new', //replycontroller post방식
            data : JSON.stringify(reply), //reply(js객체 -> json으로 변환)
            contentType : "application/json; charset=utf-8",
            
            success : function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error : function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });

    } //end add

    function getList(param, callback, error){
        let bno = param.bno;
      
        let page = param.page || 1; //값이 있으며 param.page에 넣고,없으면 1이나옴 
        
        $.ajax({
            type : 'get', 
            url : '/replies/pages/'+ bno + "/" + page,

            success : function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error : function(xhr, status, er){
                if(error){
                    error(er);
                }
        }
    })
} //end getList

    function remove(rno, callback, error){

        $.ajax({
            type:'delete',
            url: '/replies/' + rno,
            
            success : function(deleteResult, status, xhr){
                if(callback){
                    callback(deleteResult);
                }
            },
            error : function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })

    } //end remove
 
    function update(reply, callback, error){
        
    }; //end update


return {
        add:add,
        getList:getList,
        remove: remove,
    };
})();