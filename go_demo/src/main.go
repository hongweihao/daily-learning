package main

import (
	"fmt"
	"go_demo/src/excel"
)

func main() {
	/*sendEmail := email.NewSendEmail("Karl", "Karl1234", "smtp.exmail.qq.com", "karl.hong@xinda.im", 465)
	err := sendEmail.SendEmail("subject", "message", "1245215272@qq.com")
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
