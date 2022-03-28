package com.example.todoapp.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.domain.entity.TaskDomain
import com.example.todoapp.domain.state.TaskListUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        todoViewModel.getAllTask()
        return ComposeView(requireContext()).apply {
            setContent {
                //TaskItemScreen(TaskDomain(1, "hello", "hello", true)) {}
                val data by todoViewModel.uiState.collectAsState(initial = TaskListUiState())
                if (data.loading) {
                    // Do nothing
                } else if (!data.loading) {
                    MainTaskScreen(data, onTaskItemChecked = { taskDomain ->
                        todoViewModel.updateTask(taskDomain)
                    })
                }
            }
        }


    }

    @Composable
    fun MainTaskScreen(taskListUiState: TaskListUiState, onTaskItemChecked: (TaskDomain) -> Unit) {
        TasksRecyclerScreen(
            taskListUiState = taskListUiState,
            onTaskItemClicked = onTaskItemChecked
        )

    }

    @Composable
    fun TasksRecyclerScreen(
        modifier: Modifier = Modifier,
        taskListUiState: TaskListUiState,
        onTaskItemClicked: (TaskDomain) -> Unit
    ) {
        LazyColumn(modifier = modifier) {
            items(taskListUiState.data) { item ->
                TaskItemScreen(taskItem = item, onTaskItemClicked = onTaskItemClicked)
            }
        }

    }


    @Composable
    fun TaskItemScreen(taskItem: TaskDomain, onTaskItemClicked: (TaskDomain) -> Unit) {
        //var checkedState by remember { mutableStateOf(taskItem.status) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = taskItem.status,//checkedState,
                onCheckedChange = {
                    taskItem.status = it
                    //checkedState = it
                    onTaskItemClicked(taskItem)
                }
            )

            Text(
                text = taskItem.description,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }

    @Preview
    @Composable
    fun TaskListPreview() {
        //TaskItemScreen(TaskDomain(1, "hello", "hello", true){})
    }
}

