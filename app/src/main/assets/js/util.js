(function (win, util) {
	var doc = win.document;
	
	//路径配置
	util.domain='http://m1607.cxhlkj.net' ;
    util.path = '/hwl-web';
    util.dictUrl = '/hwl-base-web/dict/tree'; // 数据字典请求路径
    
    if (mui.os.android || mui.os.ios) {
    	util.path = util.domain+ util.path;
    	util.dictUrl = util.domain + util.dictUrl;
    }
    util.dictType = 'hwl';//默认数据字典类型
    util.portalKey = 'hwl';//数据字典门户
    util.themeColor = '#ffb82f'; // 主题色

	//接口定义
    var urls = {
    	//通用接口：测试使用，你们不能调用，自己写
    	create: '/crud/${0}/create',
        update: '/crud/${0}/update',
        readById: '/crud/${0}/readById',
        count: '/crud/${0}/count',
        readList: '/crud/${0}/readList',
        readTree: '/crud/${0}/readTree',
        readAndCount: '/crud/${0}/readAndCount',
        logicDelMult: '/crud/${0}/logicDelMult',
        
        //其他接口
        login: '', // 登录
        register: '', // 注册
        forgetPwd: '' // 忘记密码
    }
	
	//获取url接口
    util.getURL = function(key,param) {
        return this.mix((this.path + urls[key]), param);
    }

    // 获取url所带参数对象：使用：var obj=utils.param;obj含url后的所有参数
    util.param = (function() {
        var obj = {};
        var list = location.search.substring(1).split('&');
        var len = list.length;
        var i = 0;
        for (; i < len; i++) {
            var arr = list[i].split('=');
            obj[arr[0]] = arr[1];
        }
        return obj;
    })();
    
	//根据id获取对象
    win.$get = function(id) {
        return doc.getElementById(id);
    }
    HTMLElement.prototype.on = function(eventType, callback) {
    	this._listeners = this._listeners || {};
    	this._listeners[eventType] = callback;
        this.addEventListener(eventType, callback, false);
        return this;
    }
    HTMLElement.prototype.off = function (eventType) {
    	this._listeners = this._listeners || {};
    	if (typeof this._listeners[eventType] === 'function') {
    		this.removeEventListener(eventType, this._listeners[eventType]);
    		delete this._listeners[eventType];
    	}
    	return this;
    }
    HTMLElement.prototype.trigger = function (eventType) {
    	var evt = doc.createEvent('HTMLEvents');
    	evt.initEvent(eventType, true, true);
    	this.dispatchEvent(evt);
    	return this;
    }
	
    util.isWeb = function () {
    	return !mui.os.android && !mui.os.ios;
    }
    
    util.mix = function(str, group) {
        return str.replace(/\$\{[^{}]+\}/gm, function(m, n) {
            n = m.slice(2, -1);
            return (group[n] != void 1) ? group[n] : '';
        });
    }
    // 让web也能够执行plusReady
    if (util.isWeb()) {
        mui.plusReady = function(callback) {
            callback();
        }
    }
    
    // ajax请求、或get请求
    util.ajax = function (param, data, done, fail) {
    	if (typeof data === 'function') {
    		fail = done;
    		done = data;
    		data = {};
    	}
    	if (typeof param === 'string') {
    		param = {
    			url: this.getURL(param),
    			data: data,
    			success: done,
    			error: fail
    		}
    	}
    	var plusReady = typeof plus != 'undefined';
    	plusReady && plus.nativeUI.showWaiting();
    	mui.ajax({
    		url: param.url,
    		type: param.type || 'get',
    		data: param.data || {},
    		dataType: 'json',
    		timeout: 10000,
    		// headers: {'Content-Type': 'application/json'},
    		success: function (ret) {
    			if (!ret.success) {
    				mui.alert(ret.msg);
					param.success && param.success.call(this);
					return;
    			}
				param.success && param.success.call(this, ret.data || {});
    		},
    		error: function (e) {
    			mui.alert('请求出错啦：\n' + param.url);
    			param.error && param.error.call(this, e);
    		},
    		complete: function () {
    			plusReady && plus.nativeUI.closeWaiting();
    		}
    	});
    }
    
    util.get = util.ajax;

	// post请求
    util.post = function (key, data, done, fail) {
    	if (typeof data === 'function') {
    		fail = done;
    		done = data;
    		data = {};
    	}
    	this.ajax({
    		url: this.getURL(key),
    		type: 'post',
    		data: data,
    		success: done,
    		error: fail
    	});
    }
    
    /**
     * 初始化数据字典控件
     * @param {Object} dictType 对应数据库sc_dict_data表的dict_type字段
     * @param {Object} classId 里面的数组对应数据库class_id字段
     * @param {Object} pickerText 你的input中文显示框
     * @param {Object} pickerValue 你的input字典值显示框
     */
    util.initTranslator=function(dictType,classId,pickerText,pickerValue){
        var param=[{dictType:dictType,classIds:[classId]}];
        mui.ajax({
            url:util.dictUrl, 
            type:"get",
            data:{
                'portalKey':util.portalKey,
                'jsonStr':JSON.stringify(param)
            },
            dataType : "json",
            success: function (ret) {
            	var picker = new mui.PopPicker();
                var data = [];
                var list=ret.data[param[0].dictType][param[0].classIds[0]];
                //填充数据
                mui.each(list, function(index, item) {
                    var obj = {}
                    obj.value = index;
                    obj.text = item;
                    data.push(obj);
                })
                //设置数据
                picker.setData(data);
                var pickerTextCtl = $get(pickerText);
                pickerTextCtl.addEventListener('tap', function(event) {
                    picker.show(function(items) {
                        $get(pickerValue).value = items[0].value;
                        pickerTextCtl.value = items[0].text;
                        //return false;
                    });
                }, false);
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
})(window, window.util = {});
