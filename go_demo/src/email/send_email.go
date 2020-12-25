package email

import (
	"gopkg.in/gomail.v2"
)

type SendEmail struct {
	Username string `json:"username"`
	Password string `json:"password"`
	Host     string `json:"host"`
	Port     int    `json:"port"`
	Addr     string `json:"addr"`
}

func NewSendEmail(userName, password, host, addr string, port int) *SendEmail {
	return &SendEmail{
		Username: userName,
		Password: password,
		Host:     host,
		Port:     port,
		Addr:     addr,
	}
}

func (sendEmail *SendEmail) SendEmail(subject, message string, to ...string) (err error) {
	m := gomail.NewMessage()
	m.SetAddressHeader("From", sendEmail.Addr, sendEmail.Username)
	m.SetHeader("To", to...)
	m.SetHeader("Subject", subject)
	m.SetBody("text/html", message)
	d := gomail.NewDialer(sendEmail.Host, sendEmail.Port, sendEmail.Addr, sendEmail.Password)
	//d.TLSConfig = &tls.Config{InsecureSkipVerify: true}
	return d.DialAndSend(m)
}
