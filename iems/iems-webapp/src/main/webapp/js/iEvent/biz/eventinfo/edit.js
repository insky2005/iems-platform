/**
 * iEvent.biz.eventinfo.edit.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "eventInfos";

	var companyid = iEvent.current_user_companyid;
	
	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			parent.window.location.reload();
		});
		return;
	}

	var sId = iEvent.query_string("id");
	
	var sGetUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/'));
	var fnGetCallback = function(oData) {
		//alert(JSON.stringify(oData));
		iEvent.json2form(oData, $('#form_data'));
	}
	iEvent.get(sGetUrl, {}, fnGetCallback);
	
	
	var oValid =
	    $.scojs_valid('#form_data', 
			{
	    		wrapper: "div.form_group",
				rules: {
					'eventInfo.name': ['not_empty'],
					'eventInfo.memo': [{'max_length': 200}]
	    		},
	    		messages: {
	    			
	    		}
	    	}
	    );
	
	var fnPutCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				parent.window.location.reload();
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}
	}
	
	$('#form_data').submit(function(){
		if (!oValid.validate()) return false;
		
		var oData = iEvent.form2json($(this), ['_wysihtml5_mode']);
		oData.isNew = false;
		//alert(JSON.stringify(oData));
		
		var sPutUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/'));
		
		iEvent.put(sPutUrl, JSON.stringify(oData), fnPutCallback);
		
		return false;
	});
    
    
});
