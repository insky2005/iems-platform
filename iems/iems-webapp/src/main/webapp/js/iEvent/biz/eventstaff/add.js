/**
 * iEvent.biz.eventstaff.add.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "eventStaffs";

	var companyid = iEvent.current_user_companyid;
	
	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			parent.window.location = parent.window.location;
		});
		return;
	}

	var eventid = iEvent.query_string("eventid");

	if (typeof eventid == 'undefind' || eventid <= 0) {
		$.scojs_message('请进入活动页面选择具体活动', $.scojs_message.TYPE_WARN, function(){
			parent.window.location.href = 'index.do';
		});
		return;
	}
	
	var oModel = $('#modal');
	oModel.on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  //var pk = button.data('pk') // Extract info from data-* attributes
	  var title = button.data('title') || '选择企业员工';
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  var modal = $(this)
	  modal.find('.modal-title').text(title)
	  
	  var sGetCompanyEmployeesUrl = iEvent.api_url(['',sVersion,"companyEmployees"].join('/'), {'companyid': companyid});
	  var fnGetCallback = function(oData) {
		//alert(JSON.stringify(oData));
		var html = [];
		html.push('<ul>');
		$.each(oData.results, function(i){
			var result = oData.results[i];
			html.push('<li>');
			html.push('<label><input type="radio" name="" class="minimal" value="');
			html.push(result.companyEmployee.id);
			html.push('" sData=\'');
			html.push(JSON.stringify(result.companyEmployee));
			html.push('\' /> ');
			html.push(result.companyEmployee.name);
			html.push('</label>');
			html.push('</li>');
		});
		html.push('</ul>');
		modal.find('.modal-body .modal-data').html(html.join(''));
	  }
	  iEvent.get(sGetCompanyEmployeesUrl, {}, fnGetCallback);
	  
	});
	oModel.on('hidden.bs.modal', function (event) {
	  var modal = $(this)
	  modal.find('.modal-body .modal-data').html("");
	});
	
	oModel.find('.modal-footer .ok').click(function() {
		var oChecked = oModel.find('.modal-body .modal-data input:checked');
		var vVal = oChecked.val();
		
		if (vVal) {
			var oData = JSON.parse(oChecked.attr('sData'));
			
			var oDataReturn = {'eventStaff': oData};
			iEvent.json2form(oDataReturn, $('#form_data'));
			
			$('#employeeid').val(vVal);
		}
		
		oModel.modal('hide');
	});
	
	oModel.find('.modal-footer .cancel').click(function() {
		oModel.modal('hide');
	});
	
	oModel.modal();

	var oDataInit = {'eventStaff': {'companyid': companyid, 'eventid': eventid}};
	iEvent.json2form(oDataInit, $('#form_data'));

	var oValid =
    $.scojs_valid('#form_data', 
		{
    		wrapper: "div.form_group",
			rules: {
				'eventStaff.name': ['not_empty'],
				'eventStaff.duty': [{'max_length': 200}],
				'eventStaff.memo': [{'max_length': 200}]
    		},
    		messages: {
    			
    		}
    	}
    );
	
	var fnPostCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				parent.window.location = parent.window.location;
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}			
	}
	
	$('#form_data').submit(function(){
		if (!oValid.validate()) return false;
		
		var oData = iEvent.form2json($(this), ['_wysihtml5_mode']);
		oData.isNew = true;
		//alert(JSON.stringify(oData));
		
		var sPostUrl = iEvent.api_url(['',sVersion,sResource].join('/'));
		
		iEvent.post(sPostUrl, JSON.stringify(oData), fnPostCallback);
		
		return false;
	});
    
    
});
