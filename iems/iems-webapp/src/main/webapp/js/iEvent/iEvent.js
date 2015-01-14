/**
 * iEvent
 */

var iEvent = iEvent || {};

void function() {
	iEvent.host = function() {
		return window.location.protocol + "//" + window.location.host + "/" + window.location.pathname.split("/")[1];
	}();
	
	iEvent.api_prefix = function() {
		return iEvent.host + "/api/rest/v1";
	}();
	
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
    
}();
