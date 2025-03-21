import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../../../services/usuario.service';
import { Router } from '@angular/router';  // Importar Router para navegação
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

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
  selector: 'app-listar-usuarios',
  templateUrl: './listar-usuarios.component.html',
  styleUrls: ['./listar-usuarios.component.scss']

})
export class ListarUsuariosComponent implements OnInit {
  usuarios: any[] = [];
  private snackBar: MatSnackBar = new MatSnackBar;  // Injetar MatSnackBar

  displayedColumns: string[] = ['id', 'nome', 'email', 'acoes'];

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  irParaPaginaInicial(): void {
    this.router.navigate(['/principal']);  
  }

  irParaCriarUsuario() {
    this.router.navigate(['/usuarios/criar']);
  }

  editarUsuario(id: number) {
    this.router.navigate(['/usuarios/editar', id]);  
  }

  excluirUsuario(id: number) {
    if (confirm('Tem certeza de que deseja excluir este usuário?')) {
      this.usuarioService.excluirUsuario(id).subscribe({
        next: () => {
          this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
          this.snackBar.open('Usuário excluído com sucesso!', '', { duration: 3000 });
        },
        error: (err) => {
          const errorMessage = err.error?.message || 'Usuário não pode ser deletado, pois tem tarefas associadas.';
          this.snackBar.open(errorMessage, '', { duration: 5000 });
        }
      });
    }
  }


}