package excel

import (
	"fmt"
	"testing"
)

func TestExportExcel(t *testing.T) {
	file := ExportExcel()
	err := file.Save("d:/test.xls")
	if err != nil {
		fmt.Println("failed")
		return
	}
	fmt.Println("successful")
}
