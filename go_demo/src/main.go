package main

import (
	"fmt"
	"go_demo/src/excel"
)

func main() {
	/*sendEmail := email.NewSendEmail("Karl", "****", "smtp.exmail.qq.com", "from@xxx.com", 465)
	err := sendEmail.SendEmail("subject", "message", "to@xxx.com")
	if err != nil {
		fmt.Println("failed, ", err)
		return
	}*/

	file := excel.ExportExcel()
	err := file.Save("d:/test.xls")
	if err != nil {
		fmt.Println("failed")
		return
	}

	fmt.Println("successful")
}
