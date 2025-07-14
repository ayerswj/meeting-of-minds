// TypeScript To-Do List Application

interface Todo {
    id: number;
    text: string;
    completed: boolean;
    createdAt: string;
}

type FilterType = 'all' | 'active' | 'completed';

class TodoApp {
    private todos: Todo[] = [];
    private currentFilter: FilterType = 'all';
    private editingId: number | null = null;

    // DOM elements
    private todoForm!: HTMLFormElement;
    private todoInput!: HTMLInputElement;
    private todoList!: HTMLUListElement;
    private taskCount!: HTMLSpanElement;
    private clearCompletedBtn!: HTMLButtonElement;
    private filterBtns!: NodeListOf<HTMLButtonElement>;

    constructor() {
        this.todos = JSON.parse(localStorage.getItem('todos') || '[]');
        this.initializeElements();
        this.bindEvents();
        this.render();
    }

    private initializeElements(): void {
        this.todoForm = document.getElementById('todoForm') as HTMLFormElement;
        this.todoInput = document.getElementById('todoInput') as HTMLInputElement;
        this.todoList = document.getElementById('todoList') as HTMLUListElement;
        this.taskCount = document.getElementById('taskCount') as HTMLSpanElement;
        this.clearCompletedBtn = document.getElementById('clearCompleted') as HTMLButtonElement;
        this.filterBtns = document.querySelectorAll('.filter-btn');
    }

    private bindEvents(): void {
        // Form submission
        this.todoForm.addEventListener('submit', (e: Event) => {
            e.preventDefault();
            this.addTodo();
        });

        // Clear completed tasks
        this.clearCompletedBtn.addEventListener('click', () => {
            this.clearCompleted();
        });

        // Filter buttons
        this.filterBtns.forEach((btn: HTMLButtonElement) => {
            btn.addEventListener('click', (e: Event) => {
                const target = e.target as HTMLButtonElement;
                this.setFilter(target.dataset.filter as FilterType);
            });
        });

        // Keyboard shortcuts
        document.addEventListener('keydown', (e: KeyboardEvent) => {
            if (e.key === 'Escape' && this.editingId) {
                this.cancelEdit();
            }
        });
    }

    private addTodo(): void {
        const text = this.todoInput.value.trim();
        if (!text) return;

        const todo: Todo = {
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

    private deleteTodo(id: number): void {
        const todoElement = document.querySelector(`[data-id="${id}"]`) as HTMLLIElement;
        if (todoElement) {
            todoElement.classList.add('removing');
            setTimeout(() => {
                this.todos = this.todos.filter(todo => todo.id !== id);
                this.saveToLocalStorage();
                this.render();
            }, 300);
        }
    }

    private toggleTodo(id: number): void {
        const todo = this.todos.find(todo => todo.id === id);
        if (todo) {
            todo.completed = !todo.completed;
            this.saveToLocalStorage();
            this.render();
        }
    }

    private startEdit(id: number): void {
        if (this.editingId) {
            this.cancelEdit();
        }

        this.editingId = id;
        const todoElement = document.querySelector(`[data-id="${id}"]`) as HTMLLIElement;
        const textElement = todoElement.querySelector('.todo-text') as HTMLSpanElement;
        const currentText = textElement.textContent || '';

        textElement.innerHTML = `
            <input type="text" class="edit-input" value="${this.escapeHtml(currentText)}" maxlength="100">
        `;

        const editInput = textElement.querySelector('.edit-input') as HTMLInputElement;
        editInput.focus();
        editInput.select();

        editInput.addEventListener('blur', () => this.saveEdit(id));
        editInput.addEventListener('keydown', (e: KeyboardEvent) => {
            if (e.key === 'Enter') {
                this.saveEdit(id);
            } else if (e.key === 'Escape') {
                this.cancelEdit();
            }
        });
    }

    private saveEdit(id: number): void {
        const todoElement = document.querySelector(`[data-id="${id}"]`) as HTMLLIElement;
        const editInput = todoElement.querySelector('.edit-input') as HTMLInputElement;
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

    private cancelEdit(): void {
        this.editingId = null;
        this.render();
    }

    private setFilter(filter: FilterType): void {
        this.currentFilter = filter;
        
        // Update active filter button
        this.filterBtns.forEach((btn: HTMLButtonElement) => {
            btn.classList.toggle('active', btn.dataset.filter === filter);
        });

        this.render();
    }

    private clearCompleted(): void {
        this.todos = this.todos.filter(todo => !todo.completed);
        this.saveToLocalStorage();
        this.render();
    }

    private getFilteredTodos(): Todo[] {
        switch (this.currentFilter) {
            case 'active':
                return this.todos.filter(todo => !todo.completed);
            case 'completed':
                return this.todos.filter(todo => todo.completed);
            default:
                return this.todos;
        }
    }

    private getActiveCount(): number {
        return this.todos.filter(todo => !todo.completed).length;
    }

    private getCompletedCount(): number {
        return this.todos.filter(todo => todo.completed).length;
    }

    private saveToLocalStorage(): void {
        localStorage.setItem('todos', JSON.stringify(this.todos));
    }

    private createTodoElement(todo: Todo): HTMLLIElement {
        const li = document.createElement('li');
        li.className = `todo-item ${todo.completed ? 'completed' : ''}`;
        li.dataset.id = todo.id.toString();

        li.innerHTML = `
            <input type="checkbox" class="todo-checkbox" ${todo.completed ? 'checked' : ''}>
            <span class="todo-text">${this.escapeHtml(todo.text)}</span>
            <button class="todo-delete" title="Delete task">×</button>
        `;

        // Bind events
        const checkbox = li.querySelector('.todo-checkbox') as HTMLInputElement;
        const deleteBtn = li.querySelector('.todo-delete') as HTMLButtonElement;
        const textSpan = li.querySelector('.todo-text') as HTMLSpanElement;

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

    private escapeHtml(text: string): string {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    private render(): void {
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
        this.filterBtns.forEach((btn: HTMLButtonElement) => {
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
    const sampleTodos: Todo[] = [
        { id: 1, text: 'Learn TypeScript fundamentals', completed: false, createdAt: new Date().toISOString() },
        { id: 2, text: 'Build a To-Do List app with TypeScript', completed: true, createdAt: new Date().toISOString() },
        { id: 3, text: 'Master advanced TypeScript features', completed: false, createdAt: new Date().toISOString() }
    ];
    localStorage.setItem('todos', JSON.stringify(sampleTodos));
}