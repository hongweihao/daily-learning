package common

type Rsp struct {
	m map[string]interface{}
}

func NewRspOk() *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = 0
	m["error_msg"] = "OK"
	m["user_tip"] = ""
	return &Rsp{m: m}
}

func NewRspError() *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = 0
	m["error_msg"] = "系统错误"
	m["user_tip"] = "系统繁忙，请稍后再试！"
	return &Rsp{m: m}
}

func NewRsp(code int, errorMsg, userTip string) *Rsp {
	m := make(map[string]interface{}, 0)
	m["code"] = code
	m["error_msg"] = errorMsg
	m["user_tip"] = userTip
	return &Rsp{m: m}
}
