<template>
    <div id="register-body">
        <div id="register-div">
            <span id="register-title">asedrfa 注册</span>
            <div id="register-form">
                <form id="register-form-data">

                    <input class="form-input" id="tel" style="margin-bottom: 5px;" type="text" name="tel"
                        placeholder="手机号">

                    <input class="form-input" id="pwd" style="margin-bottom: 5px;" type="password" name="pwd"
                        placeholder="密码">
                    <div style="width: 100%;height: 40px;">
                        <div style="position: absolute; width: 150px;left: 25px;margin-top: 0px;font-size: 0.8em;">

                            <input class="form-input" style="height:2em" type="text" name="code" id="code"
                                placeholder="验证码">
                        </div>
                        <div style="position: absolute; width: 100px; left: 190px;margin-top: 10px;">
                            <img src="getCodeImage" alt="验证码">
                        </div>
                    </div>
                </form>
                <button id="submit-button">注册</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Register',

}
</script>

<style scoped>
#register-body {
    background: url(../assets/login.jpg) no-repeat;
    background-size: cover;
    height: 100%;
    width: 100%;
    padding: 0%;
    margin: 0%;
    overflow: hidden;
}

#register-div {
    position: relative;
    float: right;
    margin-right: 18%;
    top: 24%;
    /* border: 1px solid blue; */
    width: 340px;
    height: auto;
    text-align: center;
    transition: 2s linear;
}

#register-title {
    font: 2em sans-serif;
    font-weight: 700;
    color: rgb(120, 120, 120);
}

#register-form {
    margin-top: 25px;
    margin-bottom: 25px;
    padding-bottom: 20px;
    padding-top: 15px;
    background-color: white;
    /* border: 1px solid red; */
    border-radius: 8px;
    width: 100%;
    text-align: center;
    opacity: 0.95;
}

.form-input {
    border-top: white;
    border-left: white;
    border-right: white;
    border-color: rgb(214, 216, 218);
    /* border: 1px solid red; */
    margin-top: 16px;
    padding-left: 5px;
    width: 85%;
    height: 3em;
    font-size: 1em;
    outline: none;
}

#submit-button {
    background-color: rgb(65, 65, 65);
    border: 1px solid rgb(49, 50, 51);
    border-radius: 3px;
    margin-top: 30px;
    color: white;
    width: 90%;
    height: 3em;
}

.code-box {
    /*margin-top: 8px;*/
    height: 27px;
    width: 150px;
    position: relative;
    padding: 4px;
    /* left: 20px; */
    border: 1px solid rgb(80, 78, 78);
    border-radius: 3px;
}

@media screen and (max-width: 900px) {
    #register-div {
        float: none;
        margin: auto;
        margin-top: 0%;
        top: 18%;
        transition: 2s linear;
    }
}
</style>