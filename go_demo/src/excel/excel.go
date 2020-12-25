package excel

import (
	"github.com/tealeg/xlsx"
)

func ExportExcel() *xlsx.File {
	border := xlsx.Border{
		Left:        "thin",
		LeftColor:   "5B9BD5",
		Right:       "thin",
		RightColor:  "5B9BD5",
		Top:         "thin",
		TopColor:    "5B9BD5",
		Bottom:      "thin",
		BottomColor: "5B9BD5",
	}

	tStyle := xlsx.NewStyle()
	tStyle.Alignment.Horizontal = "Center"
	tStyle.Alignment.Vertical = "Center"
	tStyle.Border = border
	tStyle.Font.Bold = true

	bgStyle := xlsx.NewStyle()
	bgStyle.Border = border
	bgStyle.Fill = *xlsx.NewFill("solid", "DDEBF7", "DDEBF7")

	bdStyle := xlsx.NewStyle()
	bdStyle.Border = border

	excel := xlsx.NewFile()
	sheet, _ := excel.AddSheet("vote")

	titleRow := sheet.AddRow()
	titleCell := titleRow.AddCell()
	titleCell.Merge(3, 0)
	titleCell.SetString("投票标题")
	titleCell.SetStyle(tStyle)

	headerRow := sheet.AddRow()
	headers := []string{"选项", "票数", "投票人员", "百分比"}
	for _, header := range headers {
		cell := headerRow.AddCell()
		cell.SetString(header)
		cell.SetStyle(bgStyle)
	}

	row1 := sheet.AddRow()
	cell1 := row1.AddCell()
	cell1.Merge(0, 1)
	cell1.SetString("投票选项")
	cell1.SetStyle(bdStyle)

	cell2 := row1.AddCell()
	cell1.Merge(0, 1)
	cell2.SetString("2")
	cell2.SetStyle(bdStyle)

	cell31 := row1.AddCell()
	cell1.Merge(0, 1)
	cell31.SetString("zhangsan")
	cell31.SetStyle(bdStyle)

	cell4 := row1.AddCell()
	cell4.SetString("%")
	cell4.SetStyle(bdStyle)

	return excel

}
