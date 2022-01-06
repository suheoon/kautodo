import React from "react";
import Todo from "./components/TaskList";
import AddTodo from "./components/AddTaskForm.js";
import "./App.css";
import {
  Paper, 
  List, 
  Container,
  Grid,
  Button,
  AppBar,
  Toolbar,
  Typography
} from "@material-ui/core";

import { 
  call,
  signout 
} from "./service/ApiService";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tasks: [],
      // componnet 생성까지 시간이 걸리는 시간 (api 호출 시 걸리는 시간)
      loading: true
    };
  }
  // react 생명주기, component 생성 이후 호출
  componentDidMount() {
    call("/tasks", "GET", null).then((response) =>
      this.setState({ tasks: response.result, loading: false })
    );
  }

  // 추가, 삭제, 수정
  add = (task) => {
    call("/tasks", "POST", task).then((response) =>
      this.setState({ tasks: response.result })
    );
  };

  delete = (task) => {
    call("/tasks", "DELETE", task).then((response) =>
      this.setState({ tasks: response.result })
    );
  };

  update = (task) => {
    call("/tasks", "PUT", task).then((response) =>
      this.setState({ tasks: response.result })
    );
  };


  render() {
    // 네비게이션 바
    var navigationBar = (
      <AppBar position="static">
        <Toolbar>
          <Grid justify="space-between" container>
            <Grid item>
              <Typography variant="h6">Kau To do</Typography>
            </Grid>
          </Grid>
            <Grid item xs ={2}>
              <Button style={{color: "white"}} onClick={signout}>
                로그아웃
              </Button>
            </Grid>
        </Toolbar>
      </AppBar>
    );

    
    var todoTasks = this.state.tasks.length > 0 && (
      <Paper style={{ margin: 16}}>
        <List>
          {this.state.tasks.map((task, idx) => (
            <Todo task={task} key={task.idx} delete={this.delete} update={this.update}/>
          ))}
        </List>
      </Paper>
    );

    // todoPage
    var todoPage = (
      <div>
        {navigationBar}
        <Container maxWidth="md">
          <AddTodo add={this.add}/>
          <div className="TodoList">{todoTasks}</div>
        </Container>
      </div>
    );
    
  return (
      <div className="App">
        {todoPage}
      </div>
    );
  }
}

export default App;
