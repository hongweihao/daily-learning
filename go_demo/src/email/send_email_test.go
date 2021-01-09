package email

import (
	"fmt"
	"testing"
)

func TestNewSendEmail(t *testing.T) {
	sendEmail := NewSendEmail("Karl", "****", "smtp.exmail.qq.com", "from@xxx.com", 465)
	err := sendEmail.SendEmail("subject", "message", "to@xxx.com")
	if err != nil {
		fmt.Println("failed, ", err)
		return
	}
}
