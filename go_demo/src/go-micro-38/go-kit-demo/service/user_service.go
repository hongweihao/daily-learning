// service 包的职责
// 1. 业务模块
package service

import (
	"context"
	"errors"
)

var (
	// 定义错误类型，避免字符串判断
	ErrorLogin    = errors.New("failed to login")
	ErrorRegister = errors.New("failed to register")
)

type UserInfo struct {
	Id       uint64
	UserName string
	Password string
}

type IUserService interface {
	Register(ctx context.Context, service *UserInfo) error
	Login(ctx context.Context, userName, password string) error
}

type UserService struct {
	//UserDao *dao.UserDao
}

func NewUserService( /*userDao *dao.UserDao*/ ) *UserService {
	return &UserService{ /*UserDao: userDao*/ }
}

func (userService *UserService) Register(ctx context.Context, service *UserInfo) error {
	if service != nil {
		return nil
	}
	return ErrorRegister
}

func (userService *UserService) Login(ctx context.Context, userName, password string) error {
	if userName == "mkii" && password == "1234" {
		return nil
	}
	return ErrorLogin
}
