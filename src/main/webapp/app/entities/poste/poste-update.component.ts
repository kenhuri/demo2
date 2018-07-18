import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPoste } from 'app/shared/model/poste.model';
import { PosteService } from './poste.service';
import { IBureau } from 'app/shared/model/bureau.model';
import { BureauService } from 'app/entities/bureau';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne';

@Component({
    selector: 'jhi-poste-update',
    templateUrl: './poste-update.component.html'
})
export class PosteUpdateComponent implements OnInit {
    private _poste: IPoste;
    isSaving: boolean;

    bureaus: IBureau[];

    personnes: IPersonne[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private posteService: PosteService,
        private bureauService: BureauService,
        private personneService: PersonneService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ poste }) => {
            this.poste = poste;
        });
        this.bureauService.query().subscribe(
            (res: HttpResponse<IBureau[]>) => {
                this.bureaus = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personneService.query().subscribe(
            (res: HttpResponse<IPersonne[]>) => {
                this.personnes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.poste.id !== undefined) {
            this.subscribeToSaveResponse(this.posteService.update(this.poste));
        } else {
            this.subscribeToSaveResponse(this.posteService.create(this.poste));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPoste>>) {
        result.subscribe((res: HttpResponse<IPoste>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackBureauById(index: number, item: IBureau) {
        return item.id;
    }

    trackPersonneById(index: number, item: IPersonne) {
        return item.id;
    }
    get poste() {
        return this._poste;
    }

    set poste(poste: IPoste) {
        this._poste = poste;
    }
}
