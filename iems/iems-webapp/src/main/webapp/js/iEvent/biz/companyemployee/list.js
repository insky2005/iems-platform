/**
 * iEvent.biz.companyemployee.list.js
 */

$(function() {
	
	var sVersion = "v1";
	var sResource = "companyEmployees";
	
	var companyid = iEvent.current_user_companyid;

	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			window.location.href = iEvent.host+'/error.do';
		});
		return;
	}
	
	var sAjaxSource = iEvent.api_url(['',sVersion,sResource].join('/'), {'companyid': companyid});
	
	var oSettings = $.extend({}, iEvent.DataTable.defaults, {
		"aoColumns": [
			{ "mData": "companyEmployee.name" },
			{ "mData": "companyEmployee.staffno" },
			{ "mData": "sysUser.username" },
			{ "mData": "sysUser.email" },
			{ "mData": "sysUser.mobile" },
			{ "mData": "sysUser.enabled" }, 
			{ "mData": "companyEmployee.id", 
			  "mRender": function ( data, type, full ) {
				var html = "";
				html += '<a href="#" class="btn btn-sm btn-primary" data-src="edit.do?id='+data+'" data-toggle="modal" data-target="#modal" data-title="修改企业员工">修改</a>';
				html += '&nbsp;&nbsp;';
				html += '<a href="#" class="btn btn-sm btn-danger" data-pk="'+data+'" data-trigger="confirm" data-action="confirm_yes" data-keyboard="true" data-cssclass="confirm_modal" data-content="确定要删除吗？" data-yes="确定" data-cancel="取消" data-target="#confirm_'+data+'">删除</a>';
		        return html;
		      }
			}
		],
		"sAjaxSource": sAjaxSource
	});
	
	var oTable = $('#table').dataTable(oSettings);
	
	var oModel = $('#modal');
	oModel.on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  //var pk = button.data('pk') // Extract info from data-* attributes
	  var title = button.data('title');
	  //alert(button.attr("href"));
	  //alert(pk);
	  var src = button.data("src");
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  var modal = $(this)
	  modal.find('.modal-title').text(title)
	  modal.find('.modal-body iframe').attr({"src": src});
	});
	oModel.on('hidden.bs.modal', function (event) {
	  var modal = $(this)
	  modal.find('.modal-body iframe').attr({"src": ""});
	});
	
	var fnDeleteCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				window.location = window.location;
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}
	};

	window["confirm_yes"] = function(options) {
		var sId = options.pk;
		
		sDeleteUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/')); 
		
		iEvent.deleet(sDeleteUrl, {}, fnDeleteCallback);
		
		return false;
	};
	
});
