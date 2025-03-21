import { RouterModule, Routes } from '@angular/router';
import { ListarUsuariosComponent } from './modules/usuarios/pages/listar-usuarios/listar-usuarios.component';
import { CriarUsuarioComponent } from './modules/usuarios/pages/criar-usuario/criar-usuario.component';
import { EditarUsuarioComponent } from './modules/usuarios/pages/editar-usuario/editar-usuario.component';
import { ListarTarefasComponent } from './modules/tarefas/pages/listar-tarefas/listar-tarefas.component';
import { CriarTarefaComponent } from './modules/tarefas/pages/criar-tarefa/criar-tarefa.component';
import { EditarTarefaComponent } from './modules/tarefas/pages/editar-tarefa/editar-tarefa.component';
import { NgModule } from '@angular/core';
import { PaginaInicialComponent } from './modules/principal/page/home-page/pagina-inicial.component';

export const routes: Routes = [
  { path: '', redirectTo: '/principal', pathMatch: 'full' },
  { path: 'principal', component: PaginaInicialComponent },
  { path: 'usuarios', component: ListarUsuariosComponent },  // Rota para listar usuários
  { path: 'usuarios/criar', component: CriarUsuarioComponent }, // Rota para criar usuário
  { path: 'usuarios/editar/:id', component: EditarUsuarioComponent }, // Rota para editar usuário
  { path: 'tarefas', component: ListarTarefasComponent },  // Rota para listar tarefas
  { path: 'tarefas/criar', component: CriarTarefaComponent }, // Rota para criar tarefa
  { path: 'tarefas/editar/:id', component: EditarTarefaComponent }, // Rota para editar tarefa
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
