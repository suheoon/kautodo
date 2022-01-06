import React from "react";
import { signin } from "../service/ApiService";
import kau_image from "../images/kau_image.png";
import {
    Container,
    Grid,
    Button,
    TextField,
    Typography,
    Link
} from "@material-ui/core";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.submitEventListenr.bind(this);
    }

    submitEventListenr(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const id = data.get("id");
        const password = data.get("password");
        signin({ userId: id, userPassword: password });
    }


    render() {
        return (
            <Container maxWidth="xs" style={{ marginTop: "10%" }}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                    <Typography variant="h4" component="div"  align="center">
                        Kau To Do
                    </Typography>
                </Grid>                                                         
                </Grid>
                    <form noValidate onSubmit={this.submitEventListenr}>
                        <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                            label="아이디"
                            variant="outlined"
                            required
                            fullWidth
                            name="id"
                            />
                        </Grid>
                    <Grid item xs={12}>
                        <TextField
                        label="비밀번호"
                        variant="outlined"
                        required
                        fullWidth
                        name="password"
                        type="password"
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        >
                        로그인
                        </Button>
                    </Grid>
                </Grid>
                <Grid container spacing={5} md={13} justify="flex-end">
                    <Grid item>
                        <Link href="/signup" variant="body2">
                            회원가입
                        </Link>
                </Grid>
                    <Grid container justify="center">
                        <img 
                        src={kau_image}
                        alt="profile"
                        width={400}
                        blurRadius={5}
                        />
                    </Grid>
            </Grid>
                </form>
            </Container>
        );
    }
}

export default Login;