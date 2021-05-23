// 上下文 Context
package gee

import (
	"encoding/json"
	"net/http"
)

type H map[string]interface{}

type Context struct {
	Req        *http.Request
	Rw         http.ResponseWriter
	Path       string
	Method     string
	Param      map[string]string
	StatusCode int
}

func NewContext(rw http.ResponseWriter, req *http.Request) *Context {
	return &Context{
		Req:    req,
		Rw:     rw,
		Path:   req.URL.Path,
		Method: req.Method,
	}
}

func (c *Context) PostForm(key string) string {
	return c.Req.PostForm.Get(key)
}

func (c *Context) Query(key string) string {
	return c.Req.URL.Query().Get(key)
}

func (c *Context) Status(code int) {
	c.StatusCode = code
	c.Rw.WriteHeader(code)
}

func (c *Context) SetHeader(key string, value string) {
	c.Rw.Header().Set(key, value)
}

func (c *Context) JSON(code int, rsp interface{}) {
	c.SetHeader("Content-Type", "application/json")
	bs, err := json.Marshal(rsp)
	if err != nil {
		c.Status(http.StatusInternalServerError)
		c.Rw.Write([]byte(err.Error()))
	}
	c.Status(code)
	c.Rw.Write(bs)
}
func (c *Context) HTML(code int, html string) {
	c.SetHeader("Content-Type", "text/html")
	c.Status(code)
	c.Rw.Write([]byte(html))
}
func (c *Context) String(code int, text string) {
	c.SetHeader("Content-Type", "text/plain")
	c.Status(code)
	c.Rw.Write([]byte(text))
}
func (c *Context) Data(code int, data []byte) {
	c.Status(code)
	c.Rw.Write(data)
}
func (c *Context) ParamGet(key string) (string, bool) {
	v, ok := c.Param[key]
	return v, ok
}
