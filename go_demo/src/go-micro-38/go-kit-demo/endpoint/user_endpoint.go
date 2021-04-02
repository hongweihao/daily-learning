// endpoint 包的职责
// 1. 定义方法级的 endpoint入口。 例如注册和登录是2个endpoint
// 2. 定义 request 的数据结构
// 3. 定义 response 的数据结构
// 4. request 数据校验？
// 5. 利用装饰器做其他的功能。例如，限流rate limit
package endpoint

import (
	"context"
	"github.com/go-kit/kit/endpoint"
	"go-kit-demo/common"
	"go-kit-demo/service"
)

type UserEndpoints struct {
	RegisterEndPoint endpoint.Endpoint
	LoginEndPoint    endpoint.Endpoint
}

type UserReq struct {
	UserName string `json:"user_name"`
	Password string `json:"password"`
}

type UserRsp struct {
	common.Rsp
}

// 如果使用这种方式会有 copy value
/*func NewUserEndpoints(userService service.IUserService) UserEndpoints{
	return UserEndpoints{
		RegisterEndPoint: MakeLoginEndpoint(userService),
		LoginEndPoint:    MakeLoginEndpoint(userService),
	}
}*/

func MakeRegisterEndpoint(userService service.IUserService) endpoint.Endpoint {
	return func(ctx context.Context, request interface{}) (interface{}, error) {
		req := request.(*UserReq)

		userInfo := &service.UserInfo{
			UserName: req.UserName,
			Password: req.Password,
		}

		if err := userService.Register(ctx, userInfo); err != nil {
			return nil, err
		}
		return common.NewRspOk(), nil
	}
}

func MakeLoginEndpoint(userService service.IUserService) endpoint.Endpoint {
	return func(ctx context.Context, request interface{}) (interface{}, error) {
		req := request.(*UserReq)
		if err := userService.Login(ctx, req.UserName, req.Password); err != nil {
			return nil, err
		}
		return common.NewRspOk(), nil
	}
}
