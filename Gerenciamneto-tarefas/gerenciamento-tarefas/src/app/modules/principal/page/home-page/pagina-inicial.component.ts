import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatTableModule,
        MatSelectModule,
        MatButtonModule
      ],
    selector: 'app-pagina-inicial',
    templateUrl: './pagina-inicial.component.html',
    styleUrls: ['./pagina-inicial.component.scss']
})
export class PaginaInicialComponent {

    constructor(private router: Router) { }

    irParaTarefas() {
        this.router.navigate(['/tarefas']);
    }

    irParaUsuarios() {
        this.router.navigate(['/usuarios']);
    }
}
