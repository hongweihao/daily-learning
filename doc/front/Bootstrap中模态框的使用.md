##### 1.模态框的代码（包含了表单）
```
<!-- 模态框，提交表单 -->
<div class="modal fade" id="finishModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">完成工单</h4>
            </div>
            <div class="modal-body">
                <form id="imageUpload" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label for="job_number" class="col-sm-3 control-label">工单号：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="job_number" name="job_number" readonly/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="image" class="col-sm-3 control-label">图片凭证：</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" name="image" id="image" multiple accept="image/*" required/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注：</label>
                        <div class="col-sm-9">
                                <textarea class="form-control" name="remark" id="remark"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="finishJob();">确认完成</button>
                <span id="tip"> </span>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
```
##### 2. 弹出模态框
```
<button class="btn btn-danger" data-toggle="modal" data-target="#finishModal">完成</button>
```

##### 3. 效果
![finish](https://img-blog.csdnimg.cn/20190401094229402.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21raWlfaG9uZw==,size_16,color_FFFFFF,t_70)