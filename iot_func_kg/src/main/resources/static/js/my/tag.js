$(document).ready(function(){
	$.ajax({
        type: 'get',
        url: base_url + 'getLexicons',
        data: '',
        dataType: 'json',
        success: function (data) {
            if(data) {
                $.each(data,function (id, lexicon) {
                	$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' value = " + id +"> "+lexicon+"\n");
                })
              
            }
        },
        error: function () {
            alert("获取词条失败");
        }
    });
	$("#submmit_btn").click(function(){
		var checked = new Array()
		$.each($('input:checkbox:checked'),function(){
			checked.push($(this).val())
        });
		alert(checked.join())
		$.ajax({
	        type: 'get',
	        url: base_url + 'setIsFunction',
	        data: {"ids" : checked.join()},
	        dataType: 'json',
	        success: function (data) {
	            alert("success")
	        },
	        error: function () {
	            alert("设置失败");
	        }
	    });
	});
});


    
