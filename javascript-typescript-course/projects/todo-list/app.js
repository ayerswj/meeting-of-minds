// To-Do List Application
class TodoApp {
    constructor() {
        this.todos = JSON.parse(localStorage.getItem('todos')) || [];
        this.currentFilter = 'all';
        this.editingId = null;
        
        this.initializeElements();
        this.bindEvents();
        this.render();
    }

    initializeElements() {
        this.todoForm = document.getElementById('todoForm');
        this.todoInput = document.getElementById('todoInput');
        this.todoList = document.getElementById('todoList');
        this.taskCount = document.getElementById('taskCount');
        this.clearCompletedBtn = document.getElementById('clearCompleted');
        this.filterBtns = document.querySelectorAll('.filter-btn');
    }

    bindEvents() {
        // Form submission
        this.todoForm.addEventListener('submit', (e) => {
            e.preventDefault();
            this.addTodo();
        });

        // Clear completed tasks
        this.clearCompletedBtn.addEventListener('click', () => {
            this.clearCompleted();
        });

        // Filter buttons
        this.filterBtns.forEach(btn => {
            btn.addEventListener('click', (e) => {
                this.setFilter(e.target.dataset.filter);
            });
        });

        // Keyboard shortcuts
        document.addEventListener('keydown', (e) => {
            if (e.key === 'Escape' && this.editingId) {
                this.cancelEdit();
            }
        });
    }

    addTodo() {
        const text = this.todoInput.value.trim();
        if (!text) return;

        const todo = {
            id: Date.now(),
            text: text,
            completed: false,
            createdAt: new Date().toISOString()
        };

        this.todos.push(todo);
        this.saveToLocalStorage();
        this.render();
        this.todoInput.value = '';
        this.todoInput.focus();
    }

    deleteTodo(id) {
        const todoElement = document.querySelector(`[data-id="${id}"]`);
        if (todoElement) {
            todoElement.classList.add('removing');
            setTimeout(() => {
                this.todos = this.todos.filter(todo => todo.id !== id);
                this.saveToLocalStorage();
                this.render();
            }, 300);
        }
    }

    toggleTodo(id) {
        const todo = this.todos.find(todo => todo.id === id);
        if (todo) {
            todo.completed = !todo.completed;
            this.saveToLocalStorage();
            this.render();
        }
    }

    startEdit(id) {
        if (this.editingId) {
            this.cancelEdit();
        }

        this.editingId = id;
        const todoElement = document.querySelector(`[data-id="${id}"]`);
        const textElement = todoElement.querySelector('.todo-text');
        const currentText = textElement.textContent;

        textElement.innerHTML = `
            <input type="text" class="edit-input" value="${currentText}" maxlength="100">
        `;

        const editInput = textElement.querySelector('.edit-input');
        editInput.focus();
        editInput.select();

        editInput.addEventListener('blur', () => this.saveEdit(id));
        editInput.addEventListener('keydown', (e) => {
            if (e.key === 'Enter') {
                this.saveEdit(id);
            } else if (e.key === 'Escape') {
                this.cancelEdit();
            }
        });
    }

    saveEdit(id) {
        const todoElement = document.querySelector(`[data-id="${id}"]`);
        const editInput = todoElement.querySelector('.edit-input');
        const newText = editInput.value.trim();

        if (newText) {
            const todo = this.todos.find(todo => todo.id === id);
            if (todo) {
                todo.text = newText;
                this.saveToLocalStorage();
            }
        }

        this.editingId = null;
        this.render();
    }

    cancelEdit() {
        this.editingId = null;
        this.render();
    }

    setFilter(filter) {
        this.currentFilter = filter;
        
        // Update active filter button
        this.filterBtns.forEach(btn => {
            btn.classList.toggle('active', btn.dataset.filter === filter);
        });

        this.render();
    }

    clearCompleted() {
        this.todos = this.todos.filter(todo => !todo.completed);
        this.saveToLocalStorage();
        this.render();
    }

    getFilteredTodos() {
        switch (this.currentFilter) {
            case 'active':
                return this.todos.filter(todo => !todo.completed);
            case 'completed':
                return this.todos.filter(todo => todo.completed);
            default:
                return this.todos;
        }
    }

    getActiveCount() {
        return this.todos.filter(todo => !todo.completed).length;
    }

    getCompletedCount() {
        return this.todos.filter(todo => todo.completed).length;
    }

    saveToLocalStorage() {
        localStorage.setItem('todos', JSON.stringify(this.todos));
    }

    createTodoElement(todo) {
        const li = document.createElement('li');
        li.className = `todo-item ${todo.completed ? 'completed' : ''}`;
        li.dataset.id = todo.id;

        li.innerHTML = `
            <input type="checkbox" class="todo-checkbox" ${todo.completed ? 'checked' : ''}>
            <span class="todo-text">${this.escapeHtml(todo.text)}</span>
            <button class="todo-delete" title="Delete task">×</button>
        `;

        // Bind events
        const checkbox = li.querySelector('.todo-checkbox');
        const deleteBtn = li.querySelector('.todo-delete');
        const textSpan = li.querySelector('.todo-text');

        checkbox.addEventListener('change', () => {
            this.toggleTodo(todo.id);
        });

        deleteBtn.addEventListener('click', () => {
            this.deleteTodo(todo.id);
        });

        textSpan.addEventListener('dblclick', () => {
            this.startEdit(todo.id);
        });

        return li;
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    render() {
        const filteredTodos = this.getFilteredTodos();
        
        // Clear the list
        this.todoList.innerHTML = '';

        // Add todo items
        filteredTodos.forEach(todo => {
            const todoElement = this.createTodoElement(todo);
            this.todoList.appendChild(todoElement);
        });

        // Update task count
        const activeCount = this.getActiveCount();
        const completedCount = this.getCompletedCount();
        
        if (this.currentFilter === 'all') {
            this.taskCount.textContent = `${activeCount} of ${this.todos.length} tasks remaining`;
        } else if (this.currentFilter === 'active') {
            this.taskCount.textContent = `${activeCount} active tasks`;
        } else {
            this.taskCount.textContent = `${completedCount} completed tasks`;
        }

        // Show/hide clear completed button
        this.clearCompletedBtn.style.display = completedCount > 0 ? 'inline' : 'none';

        // Update filter button states
        this.filterBtns.forEach(btn => {
            btn.classList.toggle('active', btn.dataset.filter === this.currentFilter);
        });
    }
}

// Initialize the app when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    new TodoApp();
});

// Add some sample data for demonstration (remove in production)
if (!localStorage.getItem('todos')) {
    const sampleTodos = [
        { id: 1, text: 'Learn JavaScript fundamentals', completed: false, createdAt: new Date().toISOString() },
        { id: 2, text: 'Build a To-Do List app', completed: true, createdAt: new Date().toISOString() },
        { id: 3, text: 'Master TypeScript', completed: false, createdAt: new Date().toISOString() }
    ];
    localStorage.setItem('todos', JSON.stringify(sampleTodos));
}