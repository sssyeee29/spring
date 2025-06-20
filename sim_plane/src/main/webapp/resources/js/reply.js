console.log("Reply Module.....");

//즉시 실행함수
let replyService = (function (){

    //DB에 값이 잘 들어갔는지
    function add(reply, callback, error){
        console.log("add reply........");

        $.ajax({
            type : 'post',
            url : '/replies/new',
            data : JSON.stringify(reply),
            contentType : "application/json; charset=utf-8",

            success : function (result, status, xhr){
                if (callback){
                    callback(result);
                }
            },
            error : function (xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    } //end add

    function getList(param, callback, error){
        let boardid = param.boardid;

        let page = param.page || 1; //값이 있으면 param.page에 넣고, 없으면 1이 나옴

        $.ajax({
            type : 'get',
            url : 'replies/pages/' + boardid + "/" + page,

            success : function (result, status, xhr){
                if(callback){
                    callback(result.replyCnt, result.list);
                }
            },
            error : function (xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })
    } //end getList

    function remove(rno, replyer, callback, error){

        $.ajax({
            type:'delete',
            url: '/replies/' + rno,
            data : JSON.stringify({rno : rno, replyer : replyer}),
            contentType: "application/json; charset=utf-8",

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

    function get(rno, callback, error){

        $.ajax({
            type: 'get',
            url: '/replies/' + rno,

            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });

    } //end get

    function update(reply, callback, error){
        $.ajax({
            type: 'put',
            url: '/replies/' + reply.rno,
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr){
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
    }; //end update

    function displayTime(timeValue){

        let today = new Date();
        let gap = today.getTime() - timeValue;

        let dateObj = new Date(timeValue);
        let str = "";

        if(gap < (1000*60*60*24)){
            let hh = dateObj.getHours();
            let mi = dateObj.getMinutes();
            let ss = dateObj.getSeconds();

            return[ (hh > 9 ? '' : '0') + hh, ":" ,
                (mi > 9 ? '' : '0') + mi, ":",
                (ss > 9 ? '' : '0') + ss
            ].join('');
        }else{
            let yy = dateObj.getFullYear();
            let mm = dateObj.getMonth()+1;
            let dd = dateObj.getDate();

            return [
                yy, '/', (mm>9? '' : '0') + mm, '/' , (dd>9? '' : '0') +dd
            ].join('');

        }
    }//end displayTime

    return {
        add:add,
        getList:getList,
        remove: remove,
        get:get,
        update:update,
        displayTime:displayTime
    };
})();