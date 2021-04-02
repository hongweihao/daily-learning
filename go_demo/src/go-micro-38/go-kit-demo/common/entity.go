package common

import "encoding/json"

type Rsp struct {
	M map[string]interface{}
}

func NewRspOk() *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = 0
	m["error_msg"] = "OK"
	m["user_tip"] = ""
	return &Rsp{M: m}
}

func NewRspError() *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = 0
	m["error_msg"] = "系统错误"
	m["user_tip"] = "系统繁忙，请稍后再试！"
	return &Rsp{M: m}
}

func NewRsp(code int, errorMsg, userTip string) *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = code
	m["error_msg"] = errorMsg
	m["user_tip"] = userTip
	return &Rsp{M: m}
}

func (rsp *Rsp) RspToJson() ([]byte, error) {
	return json.Marshal(rsp.M)
}

func (rsp *Rsp) Put(key string, value interface{}) {
	rsp.M[key] = value
}

func (rsp *Rsp) Get(key string) (interface{}, bool) {
	v, ok := rsp.M[key]
	return v, ok
}
