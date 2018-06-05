
//分页js代码
$(document).ready(function(){
	//填充词条
	fillPage(parseInt($("#currentPage").val()) + 1)
	//填充分类
	$.ajax({
        type: 'get',
        url: base_url + 'getBaseFunctions',
        data: '',
        dataType: 'json',
        success: function (data) {
            if(data) {
            	var ontologySelector = $("#baseFunctionsSelector");
                $.each(data,function (id, baseFunction) {
                	ontologySelector.append("<option value="+id+">"+baseFunction+"</option>")
                })
              
            }
        },
        error: function () {
            alert("获取词条失败");
        }
    });
	$("#nextPage_btn").click(function(){
		fillPage(parseInt($("#currentPage").val()) + 1)
	});
	$("#submmit_btn").click(function(){
		var checked = new Array()
		var checkbox = new Array()
		$.each($('input:checkbox:checked'),function(){
			checked.push($(this).val())
        });
		var baseFunctionId = $("#baseFunctionsSelector option:selected").val();
		$.ajax({
	        type: 'get',
	        url: base_url + 'setBaseFunctionId',
	        data: {"idsChecked" : checked.join(), "baseFunctionId" : baseFunctionId},
	        dataType: 'json',
	        success: function (data) {
	            alert(data.msg)
	        },
	        error: function (data) {
	            alert("设置失败!!!" + data.responseText);
	        }
	    });
	});
	
	
	$("#baseFunctionsSelector").change(function(){
		var pageNum = parseInt($("#currentPage").val());
		fillPage(pageNum)
	});

});

function fillPage(next){
	$.ajax({
        type: 'get',
        url: base_url + 'getPageLexicons',
        data: {"from" : next, "flag" : 2},
        dataType: 'json',
        success: function (data) {
        	$("#lexicon_div").empty()
        	var lexicons = eval('data.lexiconMap')
        	var totalPages = eval('data.totalPages')
        	var currentPage = eval('data.currentPage')
        	var baseFunctionId = $("#baseFunctionsSelector option:selected").val();
            if(lexicons) {
                $.each(lexicons,function () {
                	if(this.baseFunctionId == 0){
                		$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' value = " + this.id +"> "+this.lexicon+"\n");
                	}else{
                		if(this.baseFunctionId == baseFunctionId){
                    		$("#lexicon_div").append("<input type='checkbox' checked='checked' id='lexicon_ck' onclick='return false' value = " + this.id +">"+this.lexicon+"\n");
                    	}else{
                    		$("#lexicon_div").append("<input type='checkbox' id='lexicon_ck' onclick='return false' value = " + this.id +"> <label style = 'color:red'>"+this.lexicon+"<\label>\n");
                    	}
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

