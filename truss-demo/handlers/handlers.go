package handlers

import (
	"context"

	pb "truss-demo/proto"
)

// NewService returns a naïve, stateless implementation of Service.
func NewService() pb.EchoServer {
	return echoService{}
}

type echoService struct{}

func (s echoService) Echo(ctx context.Context, in *pb.EchoRequest) (*pb.EchoResponse, error) {
	var resp pb.EchoResponse
    resp.Out="My first truss demo"
	return &resp, nil
}

func (s echoService) Louder(ctx context.Context, in *pb.LouderRequest) (*pb.EchoResponse, error) {
	var resp pb.EchoResponse
	return &resp, nil
}

func (s echoService) LouderGet(ctx context.Context, in *pb.LouderRequest) (*pb.EchoResponse, error) {
	var resp pb.EchoResponse
	return &resp, nil
}
