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
//		alert(checked.length + " " + checkbox.length )
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
        data: {"from" : next},
        dataType: 'json',
        success: function (data) {
        	$("#lexicon_div").empty()
        	var lexicons = eval('data.lexiconMap')
        	var totalPages = eval('data.totalPages')
        	var currentPage = eval('data.currentPage')
            if(lexicons) {
                $.each(lexicons,function (id, lexicon) {
                	$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' value = " + id +"> "+lexicon+"\n");
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
    
