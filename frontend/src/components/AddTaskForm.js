import React from "react";

import {
    InputBase,
    Paper,
    Button
} from "@material-ui/core";


class AddTaskForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = { task: {contents: ""}};
        this.add = props.add;
    }

    onButtonClick = () => {
        if (this.state.task.contents !== "") {
            this.add(this.state.task);
            this.setState({task : {contents: ""}});
        }
    }

    enterKeyEventListenr = (event) => {
        if (event.key === 'Enter') {
            this.onButtonClick();
        }
    }

    onInputChange = (event) => {
        const thisTask = this.state.task;
        thisTask.contents = event.target.value;
        this.setState({task: thisTask});
    }

    render() {
        return (
            <Paper style={{ margin: 16, padding: 15}}>
                <form style={{ display: "flex", margin: 5 }}>
                    <InputBase
                    value={this.state.task.contents}
                    sx={{ ml: 11, flex: 1 }}
                    placeholder="작업 추가"
                    fullWidth
                    onChange={this.onInputChange}
                    onSubmit={this.enterKeyEventListenr}
                    onKeyPress={this.enterKeyEventListenr}
                    />
                    <Button 
                    type="submit" 
                    variant="text" 
                    style={{ width: "1%" }} 
                    onClick={this.onButtonClick}>
                        Add
                    </Button>
                </form>
            </Paper>
        );
    }
}

export default AddTaskForm;
