import React from "react";
import {
    Button,
    TextField,
    Link,
    Grid,
    Container,
    Typography,
} from "@material-ui/core";
import { signup } from "../service/ApiService";

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.submitEventListener.bind(this);
    }
    submitEventListener(event) {
        event.preventDefault();
        // formdata, jsong형식 key-value
        const data = new FormData(event.target);
        const userName = data.get("userName");
        const userId = data.get("userId");
        const userPassword = data.get("userPassword");
        signup({ userId: userId, userPassword: userPassword, userName: userName });
    }

    render() {
    return (
        <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
        <form noValidate onSubmit={this.submitEventListener}>
            <Grid container spacing={2}>
            <Grid item xs={12}>
                <Typography component="h1" variant="h5">
                회원가입
                </Typography>
            </Grid>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    name="userName"
                    fullWidth
                    autoFocus
                    label="이름"
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    name="userId"
                    fullWidth
                    label="아이디"
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    variant="outlined"
                    fullWidth
                    name="userPassword"
                    type="password"
                    label="비밀번호"
                />
            </Grid>
            <Grid item xs={12}>
                <Button
                    variant="contained"
                    color="primary"
                    type="submit"
                    fullWidth
                    >
                    가입하기
                </Button>
            </Grid>
            </Grid>
            <Grid container spacing={2} md={10} justify="flex-end">
                <Grid item>
                    <Typography>
                        이미 계정이 있으세요? 
                    </Typography>
                </Grid>
                <Grid item>
                    <Link href="/login" variant="body2">
                        로그인
                    </Link>
                </Grid>
            </Grid>
        </form>
        </Container>
    );
    }
}

export default SignUp;