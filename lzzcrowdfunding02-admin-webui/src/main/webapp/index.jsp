<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>INsert title here</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/" />
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn4").click(function () {
                //准备好要发送到服务器的数组
                var student = {
                    "stuId": 5,
                    "stuName": "tom",
                    "address":{
                        "province": "广东",
                        "city": "深圳",
                        "street": "后端"
                    },
                    "subjectList": [
                        {
                            "subjectName": "javaSE",
                            "subjectScore": 100
                        },
                        {
                            "subjectName": "SSM",
                            "subjectScore": 99
                        },
                    ],
                    "map": {
                        "k1": "v1",
                        "k2": "v2"
                    }
                };

                //将JSON数组转换为JSON字符串
                var requestBody = JSON.stringify(student);

                $.ajax({
                    "url": "send/compose/object.json",
                    "type": "post",
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "json", //如何对待服务端返回的数据
                    "success": function (response) {
                        alert(response);
                    },
                    "error": function (response){
                        alert(response)
                    }
                })
            });

            $("#btn3").click(function () {
                //准备好要发送到服务器的数组
                var array = [5,8,12];
                console.log(array.length)

                //将JSON数组转换为JSON字符串
                var requestBody = JSON.stringify(array);
                //"['5','8','12']"
                console.log(requestBody.length)

                $.ajax({
                    "url": "send/array/three.html",
                    "type": "post",
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "text", //如何对待服务端返回的数据
                    "success": function (response) {
                        alert(response)
                    },
                    "error": function (response){
                        alert(response)
                    }
                })
            });

            $("#btn2").click(function () {
                $.ajax({
                    "url": "send/array/two.html",
                    "type": "post",
                    "data": {
                        "array[0]": 5,
                        "array[1]": 8,
                        "array[2]": 12
                    },
                    "dataType": "text", //如何对待服务端返回的数据
                    "success": function (response) {
                        alert(response)
                    },
                    "error": function (response){
                        alert(response)
                    }
                })
            });

            $("#btn1").click(function () {
                $.ajax({
                    "url": "send/array/one.html",
                    "type": "post",
                    "data": {
                        "array": [5,8,12]
                    },
                    "dataType": "text", //如何对待服务端返回的数据
                    "success": function (response) {
                        alert(response)
                    },
                    "error": function (response){
                        alert(response)
                    }
                })
            });
        });

        $("#btn5").click(function () {
            layer.msg("弹框");
        });
    </script>
</head>
<body>
<h2>Hello World!</h2>
    <a href="test/ssm.html">测试SSM整合环境</a>

    <br/>
    <br/>

    <button id="btn1">Send [5,8,12] one</button>

    <br/>
    <br/>

    <button id="btn2">Send [5,8,12] Two</button>

    <br/>
    <br/>

    <button id="btn3">Send [5,8,12] Three</button>

    <br/>
    <br/>

    <button id="btn4">Send Compose Object</button>

    <br/>
    <br/>

    <button id="btn5">点我弹框</button>

</body>
</html>
