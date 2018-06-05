//分页js代码
$(document).ready(function(){
	fillPage()
	$("#nextPage_btn").click(function(){
		fillPage()
	});
	$("#submmit_btn").click(function(){
		var checked = new Array()
		var checkbox = new Array()
		$.each($('input:checkbox:checked'),function(){
			checked.push($(this).val())
        });
		$.each($('input:checkbox'),function(){
			checkbox.push($(this).val())
        });
		$.ajax({
	        type: 'get',
	        url: base_url + 'setIsFunction',
	        data: {"idsChecked" : checked.join(), "ids" : checkbox.join()},
	        dataType: 'json',
	        success: function (success) {
	            alert(success)
	        },
	        error: function () {
	            alert("设置失败");
	        }
	    });
	});

});

function fillPage(){
	var next = parseInt($("#currentPage").val()) + 1;
	$.ajax({
        type: 'get',
        url: base_url + 'getPageLexicons',
        data: {"from" : next, "flag" : 1},
        dataType: 'json',
        success: function (data) {
        	$("#lexicon_div").empty()
        	var lexicons = eval('data.lexiconMap')
        	var totalPages = eval('data.totalPages')
        	var currentPage = eval('data.currentPage')
            if(lexicons) {
            	$.each(lexicons,function () {
                	if(this.isFunction == 0){
                		$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' checked='checked' value = " + this.id +"> "+this.lexicon+"\n");
                	}else{
                		$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' value = " + this.id +"> "+this.lexicon+"\n");
                	}
                })
                $("#totalPages").val(totalPages);
                $("#currentPage").val(currentPage);
              
            }
        },
        error: function () {
            alert("获取词条失败");
        }
    });
}
    
