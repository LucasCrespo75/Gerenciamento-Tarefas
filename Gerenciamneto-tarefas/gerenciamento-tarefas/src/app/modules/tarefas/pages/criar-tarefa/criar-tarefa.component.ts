import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TarefaService } from '../../../../services/tarefa.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { UsuarioService } from '../../../../services/usuario.service';

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
  selector: 'app-criar-tarefa',
  templateUrl: './criar-tarefa.component.html',
  styleUrls: ['./criar-tarefa.component.scss']
})
export class CriarTarefaComponent {
  tarefaForm: FormGroup;
  usuarios: any[] = []; 



  constructor(
    private tarefaService: TarefaService,
    private usuarioService: UsuarioService, 
    private router: Router,
    private fb: FormBuilder
  ) {
    this.tarefaForm = this.fb.group({
      titulo: ['', Validators.required],
      descricao: ['', Validators.required],
      status: ['PENDENTE', Validators.required],
      usuarioId: [null, Validators.required]  
    });
  }

  ngOnInit() {
    this.usuarioService.getUsuarios().subscribe((data) => {
      this.usuarios = data; 
    });
  }

  criarTarefa() {
    if (this.tarefaForm.valid) {
      this.tarefaService.criarTarefa(this.tarefaForm.value).subscribe({
        next: (data) => {
          console.log('Tarefa criada com sucesso:', data);
          this.router.navigate(['/tarefas']); 
        },
        error: (error) => {
          console.error('Erro ao criar tarefa:', error);
        }
      });
    }
  }
}