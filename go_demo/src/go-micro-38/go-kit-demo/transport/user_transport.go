// transport 包的职责：
// 1. 对外选择一种协议暴露接口，例如：http或者rpc
// 2. 定义接口 request 的反序列化方法
// 3. 定义接口的 response 的序列化方法
package transport

import (
	"context"
	"encoding/json"
	"errors"
	"github.com/gorilla/mux"
	"go-kit-demo/common"
	"go-kit-demo/endpoint"
	"net/http"

	kitHttp "github.com/go-kit/kit/transport/http"
)

var (
	//ErrorInvalidParams = errors.New("invalid parameters")
	ErrorUserName = errors.New("invalid user_name")
	ErrorPassword = errors.New("invalid password")
)

func MakeHttpHandler(ctx context.Context, endpoint endpoint.UserEndpoints) http.Handler {
	r := mux.NewRouter()

	r.Methods("POST").Path("/register").Handler(kitHttp.NewServer(
		endpoint.RegisterEndPoint,
		decodeUserReq,
		encodeRsp))

	r.Methods("POST").Path("/login").Handler(kitHttp.NewServer(
		endpoint.LoginEndPoint,
		decodeUserReq,
		encodeRsp))

	return r
}

func decodeUserReq(ctx context.Context, req *http.Request) (interface{}, error) {
	if err := req.ParseForm(); err != nil {
		return nil, err
	}
	userName := req.FormValue("user_name")
	password := req.FormValue("password")

	if userName == "" {
		return nil, ErrorUserName
	}
	if password == "" {
		return nil, ErrorPassword
	}

	userReq := &endpoint.UserReq{
		UserName: userName,
		Password: password,
	}
	return userReq, nil
}

func encodeRsp(ctx context.Context, rw http.ResponseWriter, rsp interface{}) error {
	userRsp := rsp.(*common.Rsp)

	/*bytes, err := userRsp.RspToJson()
	if err != nil {
		return err
	}

	rw.WriteHeader(200)
	rw.Write(bytes)

	return nil*/

	return json.NewEncoder(rw).Encode(userRsp.M)
}
