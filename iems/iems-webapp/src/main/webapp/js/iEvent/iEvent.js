/**
 * iEvent
 */

var iEvent = iEvent || {};

iEvent.cache = iEvent.cache || {};

void function() {
	iEvent.host = function() {
		return window.location.protocol + "//" + window.location.host + "/" + window.location.pathname.split("/")[1];
	}();
	
	iEvent.api_prefix = function() {
		return iEvent.host + "/api/rest";
	}();
	
	iEvent.api_url = function(path, params) {
		params = params || {};
		//alert(jQuery.param(params));
		return iEvent.api_prefix + path + "?" + "access_token=" + iEvent.access_token + '&' + jQuery.param(params);
	};
	
	iEvent.assets_path = function() {
		return iEvent.host + "/assets";
	}();
	
	iEvent.query_string = function(param) {
		var reg = new RegExp('(^|&)' + param + '=([^&]*)(&|$)', 'i');
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) {
	        return unescape(r[2]);
	    }
	    return null;
	};
	
	iEvent.form2json = function(form, excludeNames) {
		excludeNames = excludeNames || [];
		
		var $form = form;
		if (typeof form === 'string') {
			$form = $(form);
		}
		
		var aFields = $form.serializeArray(); //alert(JSON.stringify(aFields));
		var oData = {};
		
		try {
			jQuery.each(aFields, function(i, field){

				if (excludeNames.join(',').indexOf(this.name) != -1) {
					return;
				}
				
				var sDataKey = "oData";
				var aNames = this.name.split('.');
				for (var i=0; i<aNames.length-1; i++) {
					sDataKey += "."+aNames[i];	//alert(typeof eval(sDataKey));
					if (typeof eval(sDataKey) == 'undefined') {
						eval(sDataKey+'={}');	//alert(eval(sDataKey));
					}
				}
				
				var vVal = eval('oData.'+this.name); //alert(vVal);
				if (typeof vVal == 'undefined') {
					eval('oData.'+this.name+'="'+(this.value || '')+'"'); //alert(eval('oData.'+this.name));
				} else {
					if (!vVal.push) {
						vVal = [vVal];
					}
					vVal.push(this.value || '');
					
					eval('oData.'+this.name+'=vVal');
				}
				
			});
		} catch (e) {
			alert(e);
		}
		
		return oData;
	};
	
	iEvent.json2form = function(oData, form) {
		var $form = form;
		if (typeof form === 'string') {
			$form = $(form);
		}
		
		var aFields = $form.serializeArray(); //alert(JSON.stringify(aFields));
		try {
			jQuery.each(aFields, function(i, field){
				var sDataKey = "oData";
				var aNames = this.name.split('.');
				for (var i=0; i<aNames.length-1; i++) {
					sDataKey += "."+aNames[i];	//alert(typeof eval(sDataKey));
					if (typeof eval(sDataKey) == 'undefined') {
						eval(sDataKey+'={}');	//alert(eval(sDataKey));
					}
				}
				
				var vVal = eval('oData.'+this.name); //alert(vVal);
				if (typeof vVal == 'undefined') {
					
				} else {
					$form.find('[name="' + this.name + '"]').val(vVal);
				}
			});
		} catch (e) {
			alert(e);
		}
	};
	
	iEvent.ajax = function(options) {
		if (jQuery) {
			jQuery.ajax(options);
		}
	};
	
	iEvent.get = function(url, data, callback, options) {
		iEvent.ajax($.extend({
			'type': 'GET', 
			'url': url, 
			'contentType': 'application/json', 
			'data': data, 
			'dataType': 'json', 
			'success': callback
		}, options||{}));
	};
	iEvent.post = function(url, data, callback) {
		iEvent.ajax({
			'type': 'POST', 
			'url': url, 
			'contentType': 'application/json', 
			'data': data, 
			'dataType': 'json', 
			'success': callback
		});
	};
	iEvent.put = function(url, data, callback) {
		iEvent.ajax({
			'type': 'POST', 
			'url': url + "&_method=PUT", 
			'contentType': 'application/json', 
			'data': data, 
			'dataType': 'json', 
			'success': callback
		});
	};
	iEvent.deleet = function(url, data, callback) {
		iEvent.ajax({
			'type': 'POST', 
			'url': url + "&_method=DELETE", 
			'contentType': 'application/json', 
			'data': data, 
			'dataType': 'json', 
			'success': callback
		});
	};
	
	iEvent.prelogin = function() {
        //use rsa to protect access_token during transfer
        var rsa = new RSAKey();
        rsa.generate(512,"10001");

        document.cookie = "modulus="+rsa.n.toString(16);
        document.cookie = "exponent="+rsa.e.toString(16);

        //private key kept on browser side, never send it out!!!
        localStorage["private"] = rsa.d.toString(16);
    };

	iEvent.access_token = function() {
		var name = "access_token";
        var access_token;
        var modulus;
        var exponent;
        var cookies=document.cookie.split("; ");

        for(var i=0;i<cookies.length;i++)
        {
            var s=cookies[i].split("=");
            if(s[0]=="access_token") {
                access_token = s[1];
            }
            if(s[0]=="modulus") {
                modulus = s[1];
            }
            if(s[0]=="exponent") {
                exponent = s[1];
            }
        }

        if( access_token && modulus && exponent && localStorage["private"]) {
            var decoder = new RSAKey();
            decoder.setPrivate(modulus,exponent,localStorage["private"]);
            return decoder.decrypt(access_token);
        }
    }();

	iEvent.current_user = function() {
		var sCurrentUrl = iEvent.api_url("/v1/users/current");
		var oReturn = null;
		iEvent.get(sCurrentUrl, {}, function(oData){
			oReturn = oData;
		}, {'async': false});

		return oReturn;
	}();

	iEvent.current_user_companyid = function() {
		if (iEvent.current_user) {
			var userid = iEvent.current_user.principal.userid;
			var usertype = iEvent.current_user.principal.usertype;
			
			var companyid = 0;
			if ('com.iems.biz.entity.UserInfo' == usertype) {
				var sGetUserInfoByUseridUrl = iEvent.api_url("/v1/userinfos/userid/"+userid);
				iEvent.get(sGetUserInfoByUseridUrl, {}, function(oData){
					//alert(JSON.stringify(oData));
					companyid = oData.userInfo.companyid;
				}, {'async': false});
			} else {
				companyid = 0;
			}
			
			return companyid;
		}
	}();
	

    iEvent.DataTable = {
    	defaults : {
    	    "bPaginate": true,
    	    "bLengthChange": false,
    	    "bFilter": false,
    	    "bSort": false,
    	    "bInfo": true,
    	    "bAutoWidth": false,
    	    "bProcessing": true,
    	    "bServerSide": true,
    	    "oLanguage" : {
    	    	"sUrl" : iEvent.assets_path + "/js/plugins/datatables/zh_CN.txt"
    	    },
    	    "sAjaxDataProp": "results"
    	}
    };
}();
