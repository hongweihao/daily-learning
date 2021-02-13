package main

import (
	"errors"
	"fmt"
	"os"
	"os/exec"
)

func main() {
	err := underlyingError(errors.New("my err"))
	fmt.Println("err:", err)
}

func underlyingError(err error) error {
	switch err := err.(type) {
	case *os.PathError:
		return err.Err
	case *os.LinkError:
		return err.Err
	case *os.SyscallError:
		return err.Err
	case *exec.Error:
		return err.Err
	}
	return err
}
