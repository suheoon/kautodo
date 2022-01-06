import React from 'react';
import {
    List,
    ListItem, 
    ListItemText,
    InputBase, 
    Checkbox,
    ListItemSecondaryAction,
    IconButton
} from "@material-ui/core";

import DeleteOutlined from "@material-ui/icons/DeleteOutlined";

class TaskList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {task: props.task, readOnly: true};
        this.delete = props.delete;
        this.update = props.update;
    }

    offReadOnlyStatus = () => {
        this.setState({readOnly: false});
    }

    enterKeyEventListener = (event) => {
        if (event.key === "Enter") {
            this.setState({readOnly: true});
            this.update(this.state.task);
        };
    }

    editEventListenr = (event) => {
        const currentItem = this.state.task;
        currentItem.contents = event.target.value;
        this.setState({task: currentItem});
    }
    
    deleteEventListener = () => {
        this.delete(this.state.task);
    }

    checkEventListener = (event) => {
        const currentItem = this.state.task;
        currentItem.done = !currentItem.done;
        this.setState({ task: currentItem });
        this.update(this.state.task);
    }

    render() {
        const task = this.state.task;
        return (
            <List>
                <ListItem>
                    <Checkbox
                        style={{
                            color: "gray"
                        }}
                        checked={task.done} 
                        onChange={this.checkEventListener}
                    />
                    <ListItemText>
                        <InputBase
                            inputProps = {{
                                "aria-label": "naked",
                                readOnly: this.state.readOnly,
                            }}
                            onClick={this.offReadOnlyStatus}
                            onKeyPress={this.enterKeyEventListener}
                            onChange={this.editEventListenr}
                            type="text"
                            id={task.idx}
                            name={task.idx}
                            value={task.contents}
                            multiline
                            fullWidth
                        />
                    </ListItemText>
                
                    <ListItemSecondaryAction>
                        <IconButton 
                        style={{color:"black"}}
                        aria-label="deleted"
                        onClick={this.deleteEventListener}>
                            <DeleteOutlined />
                        </IconButton>
                    </ListItemSecondaryAction>
                </ListItem>
            </List>
        );
    }
}

export default TaskList;