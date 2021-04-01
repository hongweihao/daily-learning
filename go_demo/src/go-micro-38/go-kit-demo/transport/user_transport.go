// transport 包的职责：
// 1. 对外选择一种协议暴露接口，例如：http或者rpc
// 2. 定义接口 request 的反序列化方法
// 3. 定义接口的 response 的序列化方法
package transport

import (
	"context"
	"go-kit-demo/endpoint"
	"net/http"
)

func MakeHttpHandler(ctx context.Context, endpoint *endpoint.UserEndpoint) http.Handler {

}

func decodeRegisterReq(ctx context.Context, req *http.Request) (interface{}, error) {
	if err := req.ParseForm(); err != nil {
		return nil, err
	}
	userName := req.FormValue("user_name")
	password := req.FormValue("password")

}

func encodeRsp(ctx context.Context, rw *http.ResponseWriter, rsp interface{}) error {

}
