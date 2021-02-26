package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

const (
	HOST     = "127.0.0.1"
	PORT     = 3306
	PASSWORD = "root"
	USERNAME = "root"
	DATABASE = "test"
)

func main() {
	db, err := getDB(USERNAME, PASSWORD, HOST, DATABASE, PORT)
	defer db.Close()
	if err != nil {
		fmt.Println("failed to get db", err)
		return
	}
	fmt.Println("connected")

	// ------------ insert -------------------
	//insertSql := `insert into t_test values(?, ?)`
	//params := []interface{}{3, "Jack"}
	//result, err := db.Exec(db, insertSql, params)
	//if err != nil {
	//	fmt.Println("failed to insert into t_test", err)
	//	return
	//}
	//lastInsertId, _ := result.LastInsertId()
	//rows, _ := result.RowsAffected()
	//fmt.Println("last insert id:", lastInsertId, ", rows affected:", rows)

	// ------- update -------
	//updateSql := `update  t_test set name = 'Karl' where id = ?`
	//params := []interface{}{2}
	//result, err := db.Exec(updateSql, params...)
	//if err != nil {
	//	fmt.Println("failed to update into", err)
	//	return
	//}
	//lastInsertId, _ := result.LastInsertId()
	//rows, _ := result.RowsAffected()
	//fmt.Println("last insert id:", lastInsertId, ", rows affected:", rows)

	// ------------ query ------------------
	params := []interface{}{1}
	rows, err := db.Query("select * from t_test where id = ?", params...)
	if err != nil {
		fmt.Println("failed to query info", err)
		return
	}
	var id int
	var name string
	for rows.Next() {
		rows.Scan(&id, &name)
		fmt.Println("id:", id, ", name:", name)
	}
}

func getDB(userName, password, host, database string, port int) (*sql.DB, error) {
	dbUrl := fmt.Sprintf("%v:%v@(%v:%v)/%v?charset=utf8", userName, password, host, port, database)
	db, err := sql.Open("mysql", dbUrl)
	if err != nil {
		fmt.Println("failed to open db", err)
		return nil, err
	}

	err = db.Ping()
	return db, err
}
